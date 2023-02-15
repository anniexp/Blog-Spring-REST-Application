package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LikeDto {
    @JsonProperty("likeId")
    private Long likeId;

    @JsonProperty("postId")
    private int postId;

    @JsonProperty("userId")
    private int userId;

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
