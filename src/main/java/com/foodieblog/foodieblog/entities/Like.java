package com.foodieblog.foodieblog.entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "likes")
public class Like {
    @Column(name = "like_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User likingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post likedPost;

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public User getLikingUser() {
        return likingUser;
    }

    public void setLikingUser(User likingUser) {
        this.likingUser = likingUser;
    }

    public Post getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(Post likedPost) {
        this.likedPost = likedPost;
    }


    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", likingUser=" + likingUser +
                ", likedPost=" + likedPost +
                '}';
    }
}
