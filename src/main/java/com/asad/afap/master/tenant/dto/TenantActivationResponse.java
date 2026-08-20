package com.asad.afap.master.tenant.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantActivationResponse {

    private String message;
    private String tenantCode;


}
