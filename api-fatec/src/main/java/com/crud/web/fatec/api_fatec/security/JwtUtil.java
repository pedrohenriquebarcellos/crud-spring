package com.crud.web.fatec.api_fatec.security;

import java.nio.charset.StandardCharsets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String chaveSecreta = "minha-chave-super-secreta-1234567890"; // 32+ caracteres

    public String extrairUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(chaveSecreta.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String gerarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                //.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 horas
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 50)) // 50 segundos
                .signWith(Keys.hmacShaKeyFor(chaveSecreta.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public boolean validarToken(String token, UserDetails userDetails) {
        final String username = extrairUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
