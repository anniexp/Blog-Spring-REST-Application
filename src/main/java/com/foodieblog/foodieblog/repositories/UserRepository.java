package com.foodieblog.foodieblog.repositories;

import com.foodieblog.foodieblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
