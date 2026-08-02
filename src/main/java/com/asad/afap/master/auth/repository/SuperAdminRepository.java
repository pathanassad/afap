package com.asad.afap.master.auth.repository;

import com.asad.afap.master.auth.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, UUID> {
    boolean existsByEmail(String email);
    Optional<SuperAdmin> findByEmail(String email);

}
