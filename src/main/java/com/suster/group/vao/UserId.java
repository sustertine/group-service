package com.suster.group.vao;

import com.suster.GroupProto;
import com.suster.UserIdProto;
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

    public UserIdProto toProto() {
        List<GroupProto> groupProtos = this.groups.stream().map(group -> group.toProto()).toList();

        return UserIdProto.newBuilder()
                .setId(this.id)
                .setUserId(this.userId)
                .addAllGroups(groupProtos)
                .build();
    }

    public static UserId toEntity(UserIdProto userIdProto) {
        UserId userId = new UserId();
        userId.setUserId(userIdProto.getUserId());
        userId.setGroups(userIdProto.getGroupsList().stream().map(groupProto -> Group.toEntity(groupProto)).toList());

        return userId;
    }

    public void addToGroup(Group group) {
        group.addUserId(this);
        this.groups.add(group);
    }
}
