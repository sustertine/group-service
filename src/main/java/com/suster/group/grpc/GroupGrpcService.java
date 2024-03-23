package com.suster.group.grpc;


import com.suster.*;
import com.suster.group.service.GroupServiceImpl;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

@GrpcService
public class GroupGrpcService implements GroupServiceProto {
    @Inject
    GroupServiceImpl groupServiceImpl;

    @Override
    public Uni<FindAllResponse> findAll(FindAllRequest request) {
        FindAllResponse findAllResponse = FindAllResponse.newBuilder().build();
            return Uni.createFrom().item(
                    FindAllResponse.newBuilder()
                            .addAllGroups(groupServiceImpl.findAll().stream().map(group -> group.toProto()).toList())
                            .build()
            );
    }

    @Override
    public Uni<CreateGroupResponse> createGroup(CreateGroupRequest request) {
        return null;
    }
}
