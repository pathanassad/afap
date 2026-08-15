package com.asad.afap.master.tenant.serviceimpl;

import com.asad.afap.master.subscription.entity.SubscriptionPlans;
import com.asad.afap.master.subscription.entity.TenantSubscription;
import com.asad.afap.master.subscription.repository.SubscriptionPlanRepository;
import com.asad.afap.master.subscription.repository.TenantSubscriptionRepository;
import com.asad.afap.master.tenant.dto.TenantCreateRequest;
import com.asad.afap.master.tenant.entity.Tenants;
import com.asad.afap.master.tenant.repository.TenantRepository;
import com.asad.afap.master.tenant.service.TenantDatabaseService;
import com.asad.afap.master.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final TenantSubscriptionRepository tenantSubscriptionRepository;
    private final TenantDatabaseService tenantDatabaseService;

    @Override
    public Tenants createTenant(TenantCreateRequest request){
        if(tenantRepository.existsByTenantCode(request.getTenantCode())){
            throw new IllegalArgumentException("Tenant Code already exists");

        }

        if(tenantRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException(("Tenant Email already exists"));
        }

        SubscriptionPlans plan  = subscriptionPlanRepository
                .findById(request.getPlanId())
                .orElseThrow(()-> new IllegalArgumentException("Subscription Plan Not Found") );

        String databaseName = "tenant_" + request.getTenantCode();

        tenantDatabaseService.createDatabase(databaseName);
        tenantDatabaseService.initializeDatabase(databaseName);

        Tenants tenant = new Tenants();
        tenant.setTenantCode(request.getTenantCode());
        tenant.setCompanyName(request.getCompanyName());
        tenant.setEmail(request.getEmail());
        tenant.setTenantSubdomain(request.getTenantSubdomain());
        tenant.setDatabaseName(databaseName);
        tenant.setStatus("ACTIVE");

        tenantRepository.save(tenant);

        TenantSubscription subscription = new TenantSubscription();
        subscription.setTenant(tenant);
        subscription.setPlan(plan);
        subscription.setStartDate(Instant.now());
        subscription.setStatus("ACTIVE");
        subscription.setCreatedAt(Instant.now());

        tenantSubscriptionRepository.save(subscription);
        return tenant;




    }
}
