package com.foodieblog.foodieblog.repositories;

import com.foodieblog.foodieblog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
