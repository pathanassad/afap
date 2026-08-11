package com.asad.afap.master.tenant.serviceimpl;

import com.asad.afap.master.tenant.config.TenantDatabaseProperties;
import com.asad.afap.master.tenant.service.TenantDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class TenantDatabaseServiceImpl implements TenantDatabaseService {

    private final TenantDatabaseProperties properties;


    @Override
    public void createDatabase(String databaseName){
        if(!databaseName.matches("^[a-zA-Z0-9_]+$]")){
            throw new IllegalArgumentException("Invalid Database Name");
        }
        String sql = "CREATE DATABASE " + databaseName;
        try (Connection connection = DriverManager.getConnection(properties.getAdminUrl(), properties.getUsername(), properties.getPassword());
        Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to create tenant database" + databaseName, e );
        }

    }

}
