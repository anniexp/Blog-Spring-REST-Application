package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public class RoleGetDto {
    @JsonProperty("roleId")
    private int roleId;

    @JsonProperty("roleName")
    private String roleName;

    @JsonProperty("users")
    private Set<UserGetDto> users;

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<UserGetDto> getUsers() {
        return users;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUsers(Set<UserGetDto> users) {
        this.users = users;
    }
}
