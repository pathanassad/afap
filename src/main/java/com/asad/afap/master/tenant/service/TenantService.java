package com.asad.afap.master.tenant.service;

import com.asad.afap.master.tenant.dto.TenantCreateRequest;
import com.asad.afap.master.tenant.dto.TenantCreateResponse;

public interface TenantService {
    TenantCreateResponse createTenant(TenantCreateRequest request);

}
