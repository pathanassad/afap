package com.asad.afap.master.tenant.repository;

import com.asad.afap.master.tenant.entity.TenantActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenantActivationTokenRepository extends JpaRepository<TenantActivationToken, UUID> {
    Optional<TenantActivationToken> findByTokenHashAndUsedFalse(String tokenHash);

}
