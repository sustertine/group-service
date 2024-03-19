package com.suster.group.vao;

import com.suster.GroupProto;
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

    public GroupProto toProto() {
        return GroupProto.newBuilder()
                .setId(this.id)
                .setName(this.name)
                .set
    }
}
