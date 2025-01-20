package com.restapi.cicd.repository;

import com.restapi.cicd.entity.SalesUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesUserRepository extends JpaRepository<SalesUser , Long> {
    boolean existsByUsername(String username);
}
