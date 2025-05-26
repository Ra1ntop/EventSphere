package com.ra1n.top.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Travkin Andrii
 * @version 25.05.2025
 */
@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "UserDetails")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String email;

    private String password;

    private RoleType role; // "USER", "ADMIN"
}
