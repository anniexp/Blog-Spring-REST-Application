package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
public class PostPostDto {
    @NotNull
    @Size(min = 2, max = 255, message = "must be between 2 and 255 characters long")
    @JsonProperty("description")
    private String description;

    @JsonProperty("authorId")
    private int authorId;

    @NotNull
    @Size(min = 1, max = 5000, message = "must be between 1 and 5000 characters long")
    @JsonProperty("text")
    private String text;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PostPostDto{" +
                "description='" + description + '\'' +
                ", authorId=" + authorId +
                ", text='" + text + '\'' +
                '}';
    }
}
