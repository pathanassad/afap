package com.asad.afap.security;

import com.asad.afap.master.auth.entity.SuperAdmin;
import com.asad.afap.master.auth.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final SuperAdminRepository superAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        SuperAdmin superAdmin = superAdminRepository
                .findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Super admin not found"));

        return User
                .withUsername(superAdmin.getEmail())
                .password(superAdmin.getPassword())
                .roles("SUPER_ADMIN")
                .build();
    }

}
