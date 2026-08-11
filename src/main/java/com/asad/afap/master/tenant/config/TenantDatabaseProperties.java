package com.asad.afap.master.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tenant.database")
public class TenantDatabaseProperties {

    private String adminUrl;
	private String username;
	private String password;


}
