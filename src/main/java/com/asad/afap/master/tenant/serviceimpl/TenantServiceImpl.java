package com.asad.afap.master.tenant.serviceimpl;

import com.asad.afap.exception.BusinessException;
import com.asad.afap.master.subscription.entity.SubscriptionPlans;
import com.asad.afap.master.subscription.entity.TenantSubscription;
import com.asad.afap.master.subscription.repository.SubscriptionPlanRepository;
import com.asad.afap.master.subscription.repository.TenantSubscriptionRepository;
import com.asad.afap.master.tenant.dto.TenantCreateRequest;
import com.asad.afap.master.tenant.dto.TenantCreateResponse;
import com.asad.afap.master.tenant.entity.Tenants;
import com.asad.afap.master.tenant.repository.TenantRepository;
import com.asad.afap.master.tenant.service.TenantActivationService;
import com.asad.afap.master.tenant.service.TenantDatabaseService;
import com.asad.afap.master.tenant.service.TenantService;
import com.asad.afap.master.tenant.service.TenantUserService;
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
    private final TenantUserService tenantUserService;
    private final TenantActivationService  tenantActivationService;




    @Override
    public TenantCreateResponse createTenant(TenantCreateRequest request){
        if(tenantRepository.existsByTenantCode(request.getTenantCode())){
            throw new BusinessException("Tenant Code already exists");

        }

        if(tenantRepository.existsByEmail(request.getEmail())){
            throw new BusinessException(("Tenant Email already exists"));
        }

        SubscriptionPlans plan  = subscriptionPlanRepository
                .findById(request.getPlanId())
                .orElseThrow(()-> new BusinessException("Subscription Plan Not Found") );

        String databaseName = "tenant_" + request.getTenantCode().toLowerCase();

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

        // create initial tenant Admin
         tenantUserService.createInitialAdmin(databaseName, request.getEmail());

         // generate activation token
        String activationToken = tenantActivationService.createActivationToken(tenant);




        return new TenantCreateResponse(
                tenant.getTenantId(),
                tenant.getTenantCode(),
                tenant.getCompanyName(),
                tenant.getEmail(),
                tenant.getDatabaseName(),
                activationToken
        );




    }
}
