package com.suster.group.dao;

import com.suster.group.vao.Group;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupRepository implements PanacheRepositoryBase<Group, Long> {
}
