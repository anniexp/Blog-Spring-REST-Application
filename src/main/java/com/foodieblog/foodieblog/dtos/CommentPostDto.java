package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentPostDto {
    @JsonProperty("postId")
    private int postId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("authorId")
    private int authorId;

    public int getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAuthorId() {
        return authorId;
    }
}
