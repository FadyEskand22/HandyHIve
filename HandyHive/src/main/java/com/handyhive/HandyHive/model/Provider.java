package com.handyhive.HandyHive.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerId;

    private String name;
    private String email;
    private String password;
    private String serviceType;
    private Double pricing;
    private String availability;
    private String description;
}
