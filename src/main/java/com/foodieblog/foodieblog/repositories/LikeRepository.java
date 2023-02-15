package com.foodieblog.foodieblog.repositories;

import com.foodieblog.foodieblog.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
