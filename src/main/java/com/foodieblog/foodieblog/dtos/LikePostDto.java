package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LikePostDto {
    @JsonProperty("postId")
    private int postId;

    @JsonProperty("userId")
    private int userId;

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "LikePostDto{" +
                "postId=" + postId +
                ", userId=" + userId +
                '}';
    }

}
