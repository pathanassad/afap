package com.asad.afap.master.tenant.controller;

import com.asad.afap.master.tenant.dto.TenantActivationRequest;
import com.asad.afap.master.tenant.dto.TenantActivationResponse;
import com.asad.afap.master.tenant.service.TenantActivationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
    public ResponseEntity<TenantActivationResponse>  activate(@Valid @RequestBody TenantActivationRequest request){
        String tenantCode = tenantActivationService.activateTenant(request.getToken(), request.getPassword());
        return ResponseEntity.ok(new TenantActivationResponse("Tenant Activated Successfully ",tenantCode));

    }
}
