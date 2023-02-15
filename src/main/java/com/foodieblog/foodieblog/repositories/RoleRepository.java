package com.foodieblog.foodieblog.repositories;

import com.foodieblog.foodieblog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleName(String roleName);
}
