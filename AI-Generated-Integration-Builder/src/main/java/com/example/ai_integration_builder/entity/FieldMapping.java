package com.example.ai_integration_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "field_mapping")
@Data
public class FieldMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "source_path")
    private String sourcePath;

    @Column(name = "target_field")
    private String targetField;

    @ManyToOne
    @JoinColumn(name = "endpoint_id")
    private IntegrationEndpoint endpoint;
}
