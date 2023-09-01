package com.spring.api.repository;

import com.spring.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseriRepository extends JpaRepository<User,Long> {
}
