package com.foodieblog.foodieblog.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "roles")
public class Role {
    @Column(name = "role_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column(name = "role_name", nullable = false)
    @Size(min = 2, max = 255, message = "must be between 2 and 255 characters long")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
