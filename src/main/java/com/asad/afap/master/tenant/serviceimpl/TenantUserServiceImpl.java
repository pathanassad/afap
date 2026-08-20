package com.asad.afap.master.tenant.serviceimpl;

import com.asad.afap.exception.BusinessException;
import com.asad.afap.master.tenant.config.PostgresProperties;
import com.asad.afap.master.tenant.service.TenantUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Service
@RequiredArgsConstructor
@Transactional
public class TenantUserServiceImpl implements TenantUserService {
        private final PostgresProperties properties;

        @Override
        public void createInitialAdmin(
                String databaseName,
                String email,
                String firstName,
                String lastName
        ){
            String url = "jdbc:postgresql://"
                    + properties.getHost()
                    + ":"
                    + properties.getPort()
                    + "/"
                    + databaseName;
            String sql = """
            INSERT INTO users 
            (email, password, role, status, first_name, last_name) 
            VALUES (?, NULL, 'ADMIN', 'PENDING', ?, ?)
                 """;

            try(Connection connection = DriverManager.getConnection(url, properties.getUsername(), properties.getPassword());
                PreparedStatement statement = connection.prepareStatement(sql);

            ) {
                statement.setString(1, email);
                statement.setString(2, firstName);
                statement.setString(3, lastName);
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
                    throw new BusinessException(
                            "Tenant Admin not Found"
                    );

                }


            } catch(BusinessException e){
                throw e;
            }
            catch(Exception e){
                throw new RuntimeException("Failed to activate Tenant Admin", e);
            }


      }
}
