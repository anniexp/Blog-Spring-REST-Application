package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserGetDto {
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("roleName")
    private String roleName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserGetDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", role=" + roleName +
                '}';
    }
}
