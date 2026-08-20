package com.asad.afap.master.tenant.service;


import com.asad.afap.master.tenant.entity.Tenants;

public interface TenantActivationService {

    String createActivationToken(Tenants tenant);

    String activateTenant(String token, String password);
}