package com.suster.group.service;


import com.suster.group.dao.GroupRepository;
import com.suster.group.vao.Group;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GroupService {
    @Inject
    GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.listAll();
    }

    public Group create(Group group) {
        groupRepository.persist(group);
        return group;
    }
}
