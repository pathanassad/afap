package com.asad.afap.master.subscription.entity;


import com.asad.afap.master.tenant.entity.Tenants;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "tenant_subscription", schema = "afap")
public class TenantSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenants tenant;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private SubscriptionPlans plan;

    private Instant startDate;

    private Instant endDate;

    private String status;

    private Instant createdAt;



}
