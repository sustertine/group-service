package com.suster.group.service;


import com.suster.CreateGroupRequest;
import com.suster.group.dao.GroupRepository;
import com.suster.group.dao.UserIdRepository;
import com.suster.group.vao.Group;
import com.suster.group.vao.UserId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GroupServiceImpl {
    @Inject
    GroupRepository groupRepository;

    @Inject
    UserIdRepository userIdRepository;

    public List<Group> findAll() {
        return groupRepository.listAll();
    }

    @Transactional
    public Group create(CreateGroupRequest createGroupRequest) {
        Group newGroup = new Group();
        newGroup.setName(createGroupRequest.getName());

        createGroupRequest.getUserIdsList().forEach(userId -> {
            UserId existingUserId = getOrCreateUserId(userId);
            existingUserId.addToGroup(newGroup);
            userIdRepository.persist(existingUserId);
        });

        newGroup.setTimetableId(createGroupRequest.getTimetableId());
        groupRepository.persist(newGroup);
        return newGroup;
    }

    public UserId getOrCreateUserId(Long userId) {
        Optional<UserId> existingUserId = userIdRepository.findByUserIdOptional(userId);

        if(existingUserId.isPresent()) return existingUserId.get();

        UserId newUserId = new UserId();
        newUserId.setUserId(userId);
        userIdRepository.persist(newUserId);

        return newUserId;
    }
}
