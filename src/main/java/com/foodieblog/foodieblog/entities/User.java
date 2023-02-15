package com.foodieblog.foodieblog.entities;

import javax.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 40, name = "password")
    private String password;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;
    @JsonIgnore
    @OneToMany(targetEntity = Post.class, mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Post> posts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role", nullable = false, referencedColumnName = "role_id")
    private Role role;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @JsonIgnore
    @OneToMany(targetEntity = Like.class, mappedBy = "likingUser", cascade = CascadeType.ALL)
    private Set<Like> userLikes;
    @JsonIgnore
    @OneToMany(targetEntity = Comment.class, mappedBy = "authorUsername", cascade = CascadeType.ALL)
    private Set<Comment> userComments;

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Role getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                '}';
    }
}
