package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class PostGetDto {
    @JsonProperty("postId")
    private int postId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("authorUsernamee")
    private String authorUsernamee;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("text")
    private String text;

    @JsonProperty("numberLikes")
    private int numberLikes;

    public void setNumberLikes(int numberLikes) {
        this.numberLikes = numberLikes;
    }

    public int getPostId() {
        return postId;
    }

    public void setAuthorUsernamee(String authorUsernamee) {
        this.authorUsernamee = authorUsernamee;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PostGetDto{" +
                "postId=" + postId +
                ", description='" + description + '\'' +
                ", authorUsernamee='" + authorUsernamee + '\'' +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                '}';
    }
}
