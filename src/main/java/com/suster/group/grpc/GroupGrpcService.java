package com.suster.group.grpc;


import com.suster.*;
import com.suster.group.service.GroupServiceImpl;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@GrpcService
public class GroupGrpcService implements GroupServiceProto {
    @Inject
    GroupServiceImpl groupServiceImpl;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    @Blocking
    public Uni<FindAllResponse> findAll(FindAllRequest request) {

            return Uni.createFrom()
                    .item(
                    FindAllResponse.newBuilder()
                            .addAllGroups(groupServiceImpl.findAll().stream().map(group -> group.toProto()).toList())
                            .build()
                    )
                    .runSubscriptionOn(executorService);
    }

    @Override
    public Uni<CreateGroupResponse> createGroup(CreateGroupRequest request) {
        return null;
    }
}
