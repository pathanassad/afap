package com.asad.afap.master.tenant.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TenantCreateRequest {

    private String tenantCode;
    private String companyName;
    private String email;
    private String tenantSubdomain;
    private UUID planId;


}
