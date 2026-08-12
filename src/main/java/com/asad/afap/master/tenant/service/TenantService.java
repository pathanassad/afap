package com.asad.afap.master.tenant.service;

import com.asad.afap.master.tenant.dto.TenantCreateRequest;
import com.asad.afap.master.tenant.entity.Tenants;

public interface TenantService {
    Tenants createTenant(TenantCreateRequest request);

}
