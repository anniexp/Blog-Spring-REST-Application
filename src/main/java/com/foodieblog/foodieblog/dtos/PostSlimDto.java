package com.foodieblog.foodieblog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
public class PostSlimDto {
    @JsonProperty("postId")
    private int postId;

   @JsonProperty("description")
   @Size(min = 2, max = 255)
   private String description;

    @JsonProperty("createdAt")
    private Date createdAt;

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
