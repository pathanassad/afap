package com.asad.afap.master.seeders;

import com.asad.afap.master.auth.entity.SuperAdmin;
import com.asad.afap.master.auth.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class SuperAdminSeeder implements CommandLineRunner {

    private final SuperAdminRepository superAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String @NonNull ... args){

        if(superAdminRepository.existsByEmail("superadmin@afap.com")){
            return;
        }

        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setFirstName("Asad");
        superAdmin.setLastName("Pathan");
        superAdmin.setEmail("superadmin@afap.com");
        superAdmin.setPassword(passwordEncoder.encode("SuperAdmin@123"));
        superAdmin.setStatus("ACTIVE");
        superAdmin.setCreatedAt(Instant.now());
        superAdmin.setUpdatedAt(Instant.now());

        superAdminRepository.save(superAdmin);
        System.out.println("Super Admin seeded successfully");
    }

}
