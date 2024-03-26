package com.suster.group.grpc;

import com.suster.*;
import com.suster.group.service.GroupServiceImpl;
import com.suster.group.vao.Group;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.hibernate.annotations.processing.Find;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class GroupGrpcService implements GroupServiceProto {
    @Inject
    GroupServiceImpl groupServiceImpl;

    @Override
    public Uni<FindAllResponse> findAll(FindAllRequest request) {
        Uni<List<GroupProto>> groupProtos = groupServiceImpl.findAll();
        FindAllResponse findAllResponse = FindAllResponse.newBuilder().addAllGroups(groupProtos.map(groupProtos1 -> groupProtos1.stream().toList()));
        return Uni.createFrom().item(findAllResponse).
    }

    @Override
    public Uni<CreateGroupResponse> createGroup(CreateGroupRequest request) {
        return null;
    }
}
