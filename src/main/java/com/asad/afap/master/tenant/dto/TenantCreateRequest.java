package com.asad.afap.master.tenant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TenantCreateRequest {

    @NotBlank(message = "Tenant code is required")
    private String tenantCode;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String email;

    @NotBlank(message = "Tenant subdomain is required")
    private String tenantSubdomain;

    @NotNull(message = "Plan ID is required")
    private UUID planId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;



}
