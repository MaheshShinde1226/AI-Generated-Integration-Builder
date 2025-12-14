package com.example.ai_integration_builder.repository;

import com.example.ai_integration_builder.entity.FieldMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldMappingRepository extends JpaRepository<FieldMapping, Long> {
    List<FieldMapping> findByEndpoint_Id(Long endpointId);
}
