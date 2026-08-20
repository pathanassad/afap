package com.asad.afap.master.tenant.service;



public interface TenantUserService {
    void createInitialAdmin(
            String databaseName,
            String email,
            String firstName,
            String lastName
    );

    void activateUser(
            String databaseName,
            String email,
            String encodedPassword
    );




}
