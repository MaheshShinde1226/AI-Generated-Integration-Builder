package com.example.ai_integration_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "integration_header")
@Data
public class IntegrationHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "header_key")
    private String headerKey;

    @Column(name = "header_value")
    private String headerValue;

    @ManyToOne
    @JoinColumn(name = "end_point_id")
    private IntegrationEndpoint endpoint;
}
