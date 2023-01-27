package com.karthick.todomanager.util;

import com.karthick.todomanager.common.AccessDeniedException;
import com.karthick.todomanager.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
    private final String secretKey = "This-is-the-secret-key";

    public String generateJWT(User user) {
        long expiryDuration = System.currentTimeMillis() + 120000L;

        Date date = new Date();
        Date expiryAt = new Date(expiryDuration);

        Claims claims = Jwts.claims()
                .setIssuer(String.valueOf(user.getId()))
                .setExpiration(date)
                .setExpiration(expiryAt);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setClaims(claims)
                .compact();
    }

    public Claims validateJWT(String auth) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(auth).getBody();
        } catch (RuntimeException re) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
