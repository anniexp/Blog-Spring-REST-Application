package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentSlimDto {
    @JsonProperty("text")
    private String text;

    public CommentSlimDto( ) {
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
