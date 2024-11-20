package com.strava.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenUtil {

    private static final long EXPIRATION_TIME = 3600000; //1 hr
    private static final String CLAVE = "mi_clave_secreta";  

    //IA Generativa ChatGPT
    public static String generarToken(String email) {
        long expirationTime = System.currentTimeMillis() + EXPIRATION_TIME;
        String token = email + ":" + expirationTime; 

        String encodedToken = Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8)) + "." + CLAVE;
        return encodedToken;
    }

    // Método para validar un token
    public static String validarToken(String token) {
        try {
            // Separar el token y la clave secreta
            String[] parts = token.split("\\.");
            String encodedToken = parts[0];
            String secretKey = parts[1];

            // Verificar si la clave secreta es la misma
            if (!CLAVE.equals(secretKey)) {
                throw new IllegalArgumentException("Token inválido");
            }

            // Decodificar el token
            String decodedToken = new String(Base64.getDecoder().decode(encodedToken), StandardCharsets.UTF_8);

            // Extraer el email y la fecha de expiración del token
            String[] tokenParts = decodedToken.split(":");
            String email = tokenParts[0];
            long expirationTime = Long.parseLong(tokenParts[1]);

            // Verificar si el token ha expirado
            if (System.currentTimeMillis() > expirationTime) {
                throw new IllegalArgumentException("El token ha expirado");
            }

            return email;  // Si todo es válido, se devuelve el email asociado con el token
        } catch (Exception e) {
            throw new IllegalArgumentException("Token inválido o expirado");
        }
    }
}
