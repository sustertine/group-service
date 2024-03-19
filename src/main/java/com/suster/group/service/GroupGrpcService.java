package com.suster.group.service;

import com.suster.*;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class GroupGrpcService implements GroupService {
    @Override
    public Uni<FindAllResponse> findAll(FindAllRequest request) {
        return null;
    }

    @Override
    public Uni<CreateGroupResponse> createGroup(CreateGroupRequest request) {
        // Your implementation logic here
        CreateGroupResponse.Builder responseBuilder = CreateGroupResponse.newBuilder();
        // Assuming you have a method to create a group, let's say createGroup(CreateGroupRequest request)
        // Replace it with your actual logic
//        Group createdGroup = createGroup(request);
//        responseBuilder.setGroup(createdGroup);
        return Uni.createFrom().item(responseBuilder.build());
    }
}
