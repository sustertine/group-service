package com.suster.group.vao;

import com.suster.GroupProto;
import com.suster.UserIdProto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "group")
public class Group extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserId> userIds;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private UserId adminId;

    private Long timetableId;

    public void addUserId(UserId userId) {
        userIds.add(userId);
    }

    public GroupProto toProto() {
        List<UserIdProto> userIdProtos = this.userIds.stream().map(userId -> userId.toProto()).toList();
        return GroupProto.newBuilder()
                .setId(this.id)
                .setName(this.name)
                .setAdminId(this.adminId.toProto())
                .setTimetableId(this.timetableId)
                .addAllUserIds(userIdProtos)
                .build();
    }

    public static Group toEntity(GroupProto groupProto) {
        Group group = new Group();
        group.setName(groupProto.getName());
        group.setUserIds(groupProto.getUserIdsList().stream().map(userIdProto -> UserId.toEntity(userIdProto)).toList());
        group.setAdminId(UserId.toEntity(groupProto.getAdminId()));
        group.setTimetableId(groupProto.getTimetableId());

        return new Group();
    }
}
