CREATE SCHEMA IF NOT EXISTS afap;

----------------------------------------------------------
-- SUPER ADMIN
----------------------------------------------------------

CREATE TABLE afap.super_admin (

                                  super_admin_id UUID PRIMARY KEY,

                                  first_name VARCHAR(100),

                                  last_name VARCHAR(100),

                                  email VARCHAR(255) NOT NULL UNIQUE,

                                  password VARCHAR(255) NOT NULL,

                                  last_login TIMESTAMPTZ,

                                  created_at TIMESTAMPTZ,

                                  updated_at TIMESTAMPTZ,

                                  status VARCHAR(30)

);

----------------------------------------------------------
-- TENANTS
----------------------------------------------------------

CREATE TABLE afap.tenants (

                              tenant_id UUID PRIMARY KEY,

                              tenant_code VARCHAR(100) NOT NULL UNIQUE,

                              company_name VARCHAR(255),

                              database_name VARCHAR(255),

                              status VARCHAR(30),

                              email VARCHAR(255) NOT NULL UNIQUE,

                              tenant_subdomain VARCHAR(255)

);

----------------------------------------------------------
-- SUBSCRIPTION PLANS
----------------------------------------------------------

CREATE TABLE afap.subscription_plans (

                                         plan_id UUID PRIMARY KEY,

                                         plan_name VARCHAR(100),

                                         description TEXT,

                                         price NUMERIC(10,2),

                                         created_at TIMESTAMPTZ,

                                         updated_at TIMESTAMPTZ,

                                         max_users INTEGER,

                                         max_projects INTEGER,

                                         max_api_keys INTEGER,

                                         monthly_api_limit INTEGER

);

----------------------------------------------------------
-- TENANT SUBSCRIPTION
----------------------------------------------------------

CREATE TABLE afap.tenant_subscription (

                                          id UUID PRIMARY KEY,

                                          tenant_id UUID NOT NULL,

                                          plan_id UUID NOT NULL,

                                          start_date TIMESTAMPTZ,

                                          end_date TIMESTAMPTZ,

                                          status VARCHAR(30),

                                          created_at TIMESTAMPTZ,

                                          CONSTRAINT fk_tenant_subscription_tenant
                                              FOREIGN KEY (tenant_id)
                                                  REFERENCES afap.tenants(tenant_id),

                                          CONSTRAINT fk_tenant_subscription_plan
                                              FOREIGN KEY (plan_id)
                                                  REFERENCES afap.subscription_plans(plan_id)

);