package com.asad.afap.master.tenant.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name = "tenants", schema = "afap")
public class Tenants {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tenantId;
    @Column(unique = true, nullable = false)
    private String tenantCode;

    private String companyName;
    private String databaseName;
    private String status;

    @Column(nullable = false, unique = true)
    private String email;

    private String tenantSubdomain;




}
