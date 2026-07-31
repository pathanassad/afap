package com.asad.afap.master.auth.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.Instant;

@Data
@Entity
public class SuperAdmin {

    @Id
    @Column(nullable = false)
    private String superAdminId;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;
    private Instant lastLogin;
    private Instant createdAt;


}


