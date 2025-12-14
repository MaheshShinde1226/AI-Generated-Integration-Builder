package com.example.ai_integration_builder.repository;

import com.example.ai_integration_builder.entity.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, Long> {
}
