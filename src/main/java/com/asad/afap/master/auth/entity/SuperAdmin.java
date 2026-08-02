package com.asad.afap.master.auth.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "super_admin", schema = "afap")
public class SuperAdmin {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID superAdminId;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private Instant lastLogin;
    private Instant createdAt;


    private Instant updatedAt;

    private String status;


}


