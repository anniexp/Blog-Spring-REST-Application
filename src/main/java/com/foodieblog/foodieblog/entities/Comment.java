package com.foodieblog.foodieblog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Column(name = "comment_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_username", nullable = false)
    private User authorUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post postOfComment;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdAt;

    @Column(name = "comment_text", nullable = false)
    @Size(min = 2, max = 255, message = "must be between 2 and 255 characters long")
    private String text;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public User getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(User authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Post getPostOfComment() {
        return postOfComment;
    }

    public void setPostOfComment(Post postOfComment) {
        this.postOfComment = postOfComment;
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
        return "Comment{" +
                "commentId=" + commentId +
                ", authorUsername=" + authorUsername +
                ", postOfComment=" + postOfComment +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                '}';
    }
}
