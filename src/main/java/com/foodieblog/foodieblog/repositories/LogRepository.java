package com.foodieblog.foodieblog.repositories;

import com.foodieblog.foodieblog.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
