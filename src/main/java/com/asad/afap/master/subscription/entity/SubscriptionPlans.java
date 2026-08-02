package com.asad.afap.master.subscription.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "subscription_plans", schema = "afap")
public class SubscriptionPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID planId;
    private String planName;

    private String description;

    private BigDecimal price;

    private Instant createdAt;

    private Instant updatedAt;

    private Integer maxUsers;

    private Integer maxProjects;

    private Integer maxApiKeys;

    private Integer monthlyApiLimit;



}
