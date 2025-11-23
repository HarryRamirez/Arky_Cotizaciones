package com.arky.cotizaciones.config.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {


    private static final String SECRET_KEY = "MiClaveSeguraDe32CaracteresOmasParaHS256JWT";

 // Generar la clave a partir de la cadena
    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); // Utilizar HS256
    }

    // Generar el token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token válido por 10 horas
                .signWith(getSigningKey())  // Firma el token con la clave
                .compact();
    }

    // Validar el token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Usa la clave para validar la firma
                .build()
                .parseClaimsJws(token); // Si no lanza excepción, es válido
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extraer el nombre de usuario (subject) del token JWT
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Usa la clave para validar la firma
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Extrae el "subject" (el nombre de usuario)
    }
}
