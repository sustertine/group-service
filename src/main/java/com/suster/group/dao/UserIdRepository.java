package com.suster.group.dao;

import com.suster.group.vao.UserId;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserIdRepository implements PanacheRepository<UserId> {
    public Optional<UserId> findByUserIdOptional(Long userId) {
        return find("userId", userId).firstResultOptional();
    };
}
