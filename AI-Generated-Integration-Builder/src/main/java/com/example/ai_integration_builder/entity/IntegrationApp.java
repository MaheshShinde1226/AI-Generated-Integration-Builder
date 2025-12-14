package com.example.ai_integration_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "integration_app")
@Data
public class IntegrationApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "auth_type")
    private String authType;
}
