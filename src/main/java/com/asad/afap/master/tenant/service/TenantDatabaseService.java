package com.asad.afap.master.tenant.service;


import org.springframework.stereotype.Service;

@Service
public interface TenantDatabaseService {

    void createDatabase(String databaseName);
    void initializeDatabase(String databaseName);


}
