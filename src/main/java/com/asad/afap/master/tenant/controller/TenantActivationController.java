package com.asad.afap.master.tenant.controller;

import com.asad.afap.master.tenant.dto.TenantActivationRequest;
import com.asad.afap.master.tenant.service.TenantActivationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@SecurityRequirement(name="bearerAuth")
public class TenantActivationController {
    private final TenantActivationService tenantActivationService;

    @PostMapping("/activate")
    public ResponseEntity<String>  activate(@RequestBody TenantActivationRequest request){
        tenantActivationService.activateTenant(request.getToken(), request.getPassword());
        return ResponseEntity.ok("Tenant Account Activated Successfully");

    }
}
