package com.asad.afap.master.tenant.serviceimpl;

import com.asad.afap.master.tenant.config.PostgresProperties;
import com.asad.afap.master.tenant.service.TenantUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Service
@RequiredArgsConstructor
public class TenantUserServiceImpl implements TenantUserService {
        private final PostgresProperties properties;

        @Override
        public void createInitialAdmin(
                String databaseName,
                String email
        ){
            String url = "jdbc:postgresql://"
                    + properties.getHost()
                    + ":"
                    + properties.getPort()
                    + "/"
                    + databaseName;
            String sql = """
            INSERT INTO users 
            (email, password, role, status, must_change_pasword) 
            VALUES (?, NULL, 'ADMIN', 'PENDING', FALSE)
                 """;

            try(Connection connection = DriverManager.getConnection(url, properties.getUsername(), properties.getPassword());
                PreparedStatement statement = connection.prepareStatement(sql);

            ) {
                statement.setString(1, email);
                statement.executeUpdate();

            }
            catch (Exception e) {
                throw new RuntimeException("Failed to create Tenant Admin", e);
            }

        }
      @Override
      public void activateUser(
              String databaseName,
              String email,
              String encodedPassword
      ){
            String url =  "jdbc:postgresql://"
                    + properties.getHost()
                    + ":"
                    + properties.getPort()
                    + "/"
                    + databaseName;

            String sql = """
            UPDATE users 
            SET password = ?, 
            status = 'ACTIVE', 
            updated_at = CURRENT_TIMESTAMP
            WHERE email = ? 
                """;

            try(Connection connection = DriverManager.getConnection(url, properties.getUsername(), properties.getPassword());
                PreparedStatement statement = connection.prepareStatement(sql);
            ){
                statement.setString(1, encodedPassword);
                statement.setString(2, email);
                int updated = statement.executeUpdate();

                if( updated == 0){
                    throw new IllegalArgumentException(
                            "Tenant Admin not Found"
                    );

                }

            }catch(Exception e){
                throw new RuntimeException("Failed to activate Tenant Admin", e);
            }


      }
}
