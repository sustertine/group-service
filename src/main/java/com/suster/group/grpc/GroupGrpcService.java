package com.suster.group.grpc;


import com.suster.*;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

@GrpcService
public class GroupGrpcService implements GroupServiceProto {
    @Inject
    GroupService groupService;

    @Override
    public Uni<FindAllResponse> findAll(FindAllRequest request) {
        FindAllResponse findAllResponse = FindAllResponse.newBuilder().build();
            return Uni.createFrom().item(
        );
    }

    @Override
    public Uni<CreateGroupResponse> createGroup(CreateGroupRequest request) {
        return null;
    }
}
