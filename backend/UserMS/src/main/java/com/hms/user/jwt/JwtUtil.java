package com.hms.user.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final Long JWT_TOKEN_VALIDITY = 5 * 60 * 60L; // 5 hours in seconds
    private static final String SECRET_KEY = "f6bd195067a66e603e620da0bd1e86b84f2c2875894779b3bc04045808b5d6029ac6b1038cdc6bfbb73fc18e5cf11b70238b5e72530492f78709710b866a4713";
    public String generateToken(UserDetails userDetails) {
        // Logic to generate JWT token using the email
        // This is a placeholder implementation
        Map<String, Object> claims = new HashMap<>();
        CustomUserDetails user = (CustomUserDetails) userDetails;
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("name", user.getName());
        claims.put("profileId", user.getProfileId());
        return doGenerateToken(claims, user.getEmail());
    }

    public String doGenerateToken(Map<String, Object> claims, String subject){
        // Logic to generate JWT token using the claims and subject
        // This is a placeholder implementation
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

}
