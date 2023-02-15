package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import javax.validation.constraints.Size;

public class UserPostDto {
    @JsonProperty("password")
    @NotNull
    @Size(max = 40)
    private String password;

    @JsonProperty("username")
    @NotNull
    @Size(max = 50)
    private String username;

    @JsonProperty("enabled")
    @NotNull
    private boolean enabled = true;

    @JsonProperty("roleName")
    private String roleName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return "UserPostDto{" +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", roleName=" + roleName +
                '}';
    }
}