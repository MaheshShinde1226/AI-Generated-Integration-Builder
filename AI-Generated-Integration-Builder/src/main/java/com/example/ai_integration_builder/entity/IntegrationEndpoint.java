package com.example.ai_integration_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "integration_end_point")
@Data
public class IntegrationEndpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "pagination_enabled")
    private boolean paginationEnabled;

    @Column(name = "pagination_type")
    private String paginationType;

    @Column(name = "next_page_field")
    private String nextPageField;

    @ManyToOne
    @JoinColumn(name = "integration_app_id")
    private IntegrationApp app;
}
