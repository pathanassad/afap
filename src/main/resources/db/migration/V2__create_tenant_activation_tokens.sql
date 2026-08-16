CREATE TABLE afap.tenant_activation_tokens (
                                               id UUID PRIMARY KEY ,

                                               tenant_id UUID NOT NULL,

                                               token_hash VARCHAR(255) NOT NULL UNIQUE,

                                               expires_at TIMESTAMP WITH TIME ZONE NOT NULL,

                                               used BOOLEAN NOT NULL DEFAULT FALSE,

                                               created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                               CONSTRAINT fk_activation_tenant
                                                   FOREIGN KEY (tenant_id)
                                                       REFERENCES afap.tenants(tenant_id)
                                                       ON DELETE CASCADE
);