package com.karthick.todomanager.util;

import com.karthick.todomanager.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
    private static String secretKey = "This-is-the-secret-key";

    public String generateJWT(User user) {
        long expiryDuration = System.currentTimeMillis() + 30000L;

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
}
