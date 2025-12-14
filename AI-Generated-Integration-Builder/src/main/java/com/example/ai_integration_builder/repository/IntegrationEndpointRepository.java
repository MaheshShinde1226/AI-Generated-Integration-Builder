package com.example.ai_integration_builder.repository;

import com.example.ai_integration_builder.entity.IntegrationEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegrationEndpointRepository extends JpaRepository<IntegrationEndpoint, Long> {
    Optional<IntegrationEndpoint> findByApp_Name(String appName);
}
