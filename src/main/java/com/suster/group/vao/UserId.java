package com.suster.group.vao;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class UserId extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "userId_id_seq", sequenceName = "userId_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId_id_seq")
    private Long id;

    private Long userId;

    @ManyToMany(mappedBy = "userIds")
    private List<Group> groups;
}
