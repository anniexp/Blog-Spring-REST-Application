package com.foodieblog.foodieblog.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "logs")
public class Log {
    @Column(name = "log_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;

    @Column(name = "level", nullable = false)
    @Size(min = 1, max = 100, message = "must be between 1 and 100 characters long")
    private String level;

    @Column(name = "entity")
    private String entity;

    @Column(name = "action", nullable = false)
    @Size(min = 1, max = 500, message = "must be create, update, delete")
    private String action;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdAt;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", level='" + level + '\'' +
                ", entity='" + entity + '\'' +
                ", action='" + action + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
