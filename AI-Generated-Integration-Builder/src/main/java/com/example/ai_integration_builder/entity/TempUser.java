package com.example.ai_integration_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "temp_user")
@Data
public class TempUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "source_app")
    private String sourceApp;
}
