package com.ra1n.top.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Travkin Andrii
 * @version 25.05.2025
 */
@Entity
@Table(name = "UserDetails")
public class User extends AbstractUser {
    public User() {
        super();
        setRole(RoleType.CLIENT);
    }
}
