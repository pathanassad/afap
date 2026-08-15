package com.asad.afap.master.tenant.serviceimpl;

import com.asad.afap.master.tenant.entity.TenantActivationToken;
import com.asad.afap.master.tenant.entity.Tenants;
import com.asad.afap.master.tenant.repository.TenantActivationTokenRepository;
import com.asad.afap.master.tenant.service.TenantActivationService;
import com.asad.afap.master.tenant.service.TenantUserService;
import com.asad.afap.master.tenant.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantActivationServiceImpl implements TenantActivationService {

    private final TenantActivationTokenRepository tenantActivationTokenRepository;
    private final TenantUserService tenantUserService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String createActivationToken(Tenants tenant){
        String rawToken = UUID.randomUUID().toString();
        String tokenHash = passwordEncoder.encode(rawToken);

        TenantActivationToken activationToken = new TenantActivationToken();
        activationToken.setTenant(tenant);
        activationToken.setTokenHash(tokenHash);
        activationToken.setExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS));
        activationToken.setCreatedAt(Instant.now());

        tenantActivationTokenRepository.save(activationToken);

        return tokenHash;

    }

    public void activateTenant(
            String token,
            String password
    ){
        String tokenHash = TokenUtils.sha256(token);

        TenantActivationToken activationToken = tenantActivationTokenRepository.findByTokenHashAndUsedFalse(tokenHash)
                .filter(t -> t.getExpiresAt().isAfter(Instant.now()))
                .orElseThrow(()-> new IllegalArgumentException("Invalid or expired Activation Token"));
        Tenants tenant = activationToken.getTenant();

        String encodedPassword = passwordEncoder.encode(password);

        tenantUserService.activateUser(
                tenant.getDatabaseName(),
                tenant.getEmail(),
                encodedPassword
        );
        activationToken.setUsed(true);

        tenantActivationTokenRepository.save(activationToken);

    }



}
