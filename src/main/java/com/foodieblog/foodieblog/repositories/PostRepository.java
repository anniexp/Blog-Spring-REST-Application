package com.foodieblog.foodieblog.repositories;

import com.foodieblog.foodieblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByDescription(String description);
}
