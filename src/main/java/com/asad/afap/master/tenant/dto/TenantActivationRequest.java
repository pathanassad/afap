package com.asad.afap.master.tenant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantActivationRequest {

    @NotBlank(message = "token is required")
    private String token;

    @NotBlank(message = "password is required")
    private String password;

}
