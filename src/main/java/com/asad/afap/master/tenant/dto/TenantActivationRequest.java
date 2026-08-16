package com.asad.afap.master.tenant.dto;

import lombok.Data;

@Data
public class TenantActivationRequest {

    private String token;
    private String password;

}
