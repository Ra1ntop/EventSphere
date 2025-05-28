package com.ra1n.top.userservice.config;

import com.ra1n.top.userservice.model.AbstractUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static com.ra1n.top.userservice.config.JwtService.CLAIM_ROLES;

/**
 * @author Travkin Andrii
 * @version 25.05.2025
 */
@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(AbstractUser user) {
        return Jwts.builder()
                .setSubject(user.getId())
                .claim(CLAIM_ROLES, List.of(user.getRole()))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(expiration, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

}
