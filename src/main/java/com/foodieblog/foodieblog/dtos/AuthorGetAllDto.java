package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class AuthorGetAllDto {
     @JsonProperty("id")
    private Long userId;

     @JsonProperty("username")
    private String username;

    @JsonProperty("posts")
    private Set<PostSlimDto> posts;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPosts(Set<PostSlimDto> posts) {
        this.posts = posts;
    }
}
