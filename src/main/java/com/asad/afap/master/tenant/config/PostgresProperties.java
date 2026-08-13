package com.asad.afap.master.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "postgres")
public class PostgresProperties {

    private String host;
    private int port;
    private String username;
    private String password;



}
