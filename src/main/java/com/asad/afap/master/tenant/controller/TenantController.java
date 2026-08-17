package com.asad.afap.master.tenant.controller;


import com.asad.afap.master.tenant.dto.TenantCreateRequest;
import com.asad.afap.master.tenant.dto.TenantCreateResponse;
import com.asad.afap.master.tenant.service.TenantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
@SecurityRequirement(name="bearerAuth")
public class TenantController {
    private final TenantService tenantService;

    @PostMapping("/create-tenant")
    public ResponseEntity<TenantCreateResponse> createTenant(
            @RequestBody TenantCreateRequest request
    ){
        TenantCreateResponse response = tenantService.createTenant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

}
