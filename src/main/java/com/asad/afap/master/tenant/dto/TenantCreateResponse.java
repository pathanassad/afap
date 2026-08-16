package com.asad.afap.master.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TenantCreateResponse {
    private UUID  tenantId;
    private String tenantCode;
    private String companyName;
    private String email;
    private String databaseName;
    private String activationToken;

}
