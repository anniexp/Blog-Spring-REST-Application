package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentGetAllDto {
    @JsonProperty("commentId")
    private Long commentId;

    @JsonProperty("postId")
    private int postId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("authorUsernamee")
    private String authorUsernamee;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthorUsernamee(String authorUsernamee) {
        this.authorUsernamee = authorUsernamee;
    }
}
