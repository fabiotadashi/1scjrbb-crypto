package br.com.fiap.cryptobb.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    @Value("${jwt.expiration}")
    private int expirationMinutes;

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(String username){
        Date issuedAt = Date.from(LocalDateTime.now().toInstant(OffsetDateTime.now().getOffset()));
        Date expireAt = Date.from(LocalDateTime.now().plusMinutes(expirationMinutes).toInstant(OffsetDateTime.now().getOffset()));
        Map<String, Object> claims = new HashMap<>(); // Roles

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token) // Token sem prefixo Bearer
                .getBody()
                .getSubject();
    }

}
