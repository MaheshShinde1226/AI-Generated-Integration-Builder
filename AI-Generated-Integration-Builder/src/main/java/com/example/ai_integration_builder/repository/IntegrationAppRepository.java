package com.example.ai_integration_builder.repository;

import com.example.ai_integration_builder.entity.IntegrationApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegrationAppRepository extends JpaRepository<IntegrationApp, Long> {
    Optional<IntegrationApp> findByName(String name);
}
