package com.asad.afap.master.tenant.repository;


import com.asad.afap.master.tenant.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TenantRepository extends JpaRepository<Tenants, UUID> {
    void createDatabase(String databaseName);


    boolean existsByTenantCode(String tenantCode);


    boolean existsByEmail(String email);
}
