package com.foodieblog.foodieblog.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posts")
public class Post {
    @Column(name = "post_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(name = "description", nullable = false)
    @Size(min = 2, max = 255, message = "must be between 2 and 255 characters long")
    private String description;

    @Column(name = "text", nullable = false)
    @Size(min = 1, max = 5000, message = "must be between 1 and 5000 characters long")
    private String text;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = true)
    private User author;

    @ToString.Exclude
    @OneToMany(targetEntity = Like.class, mappedBy = "likedPost", cascade = CascadeType.ALL)
    private Set<Like> postLikes;

    @ToString.Exclude
    @OneToMany(targetEntity = Comment.class, mappedBy = "postOfComment", cascade = CascadeType.ALL)
    private Set<Like> userComments;


    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setPostLikes(Set<Like> postLikes) {
        this.postLikes = postLikes;
    }

    public void setUserComments(Set<Like> userComments) {
        this.userComments = userComments;
    }

    public Long getPostId() {
        return postId;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public Set<Like> getPostLikes() {
        return postLikes;
    }

    public Set<Like> getUserComments() {
        return userComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", author=" + author +
                '}';
    }
}

