CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                       email VARCHAR(255) NOT NULL UNIQUE,

                       password VARCHAR(255),

                       first_name VARCHAR(100),

                       last_name VARCHAR(100),

                       role VARCHAR(50) NOT NULL,

                       status VARCHAR(30) NOT NULL DEFAULT 'PENDING',

                       must_change_password BOOLEAN NOT NULL DEFAULT FALSE,

                       created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE projects (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                          name VARCHAR(255) NOT NULL,

                          description TEXT,

                          status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

                          created_by UUID NOT NULL,

                          created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_project_created_by
                              FOREIGN KEY (created_by)
                                  REFERENCES users(id)
);

CREATE TABLE project_members (
                                 project_id UUID NOT NULL,

                                 user_id UUID NOT NULL,

                                 role VARCHAR(50) NOT NULL DEFAULT 'MEMBER',

                                 joined_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                 PRIMARY KEY (project_id, user_id),

                                 CONSTRAINT fk_project_member_project
                                     FOREIGN KEY (project_id)
                                         REFERENCES projects(id)
                                         ON DELETE CASCADE,

                                 CONSTRAINT fk_project_member_user
                                     FOREIGN KEY (user_id)
                                         REFERENCES users(id)
                                         ON DELETE CASCADE
);

CREATE TABLE api_keys (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                          user_id UUID NOT NULL,

                          key_name VARCHAR(100) NOT NULL,

                          key_hash VARCHAR(255) NOT NULL UNIQUE,

                          status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

                          created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          expires_at TIMESTAMP WITH TIME ZONE,

                          CONSTRAINT fk_api_key_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  ON DELETE CASCADE
);