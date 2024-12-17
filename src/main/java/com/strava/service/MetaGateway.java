package com.strava.service;

import org.springframework.stereotype.Component;
import java.io.*;
import java.net.Socket;

@Component
public class MetaGateway {

    private static final String META_SERVER_HOST = "localhost"; // Cambia si el server está en otra IP
    private static final int META_SERVER_PORT = 12345;          // Puerto donde escucha socket-meta-server

    // Verificar si el email está registrado
    public boolean checkEmail(String email) {
        String request = "CHECK_EMAIL," + email;
        String response = sendRequest(request);
        return "EMAIL_FOUND".equals(response);
    }

    // Validar login con email y password
    public boolean login(String email, String password) {
        String request = "VALIDATE_LOGIN," + email + "," + password;
        String response = sendRequest(request);
        return "LOGIN_SUCCESS".equals(response);
    }

    // Método para enviar una solicitud al servidor Meta
    private String sendRequest(String request) {
        try (Socket socket = new Socket(META_SERVER_HOST, META_SERVER_PORT);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Enviar solicitud
            writer.write(request);
            writer.newLine();
            writer.flush();

            // Leer respuesta
            return reader.readLine();

        } catch (IOException e) {
            throw new RuntimeException("Error al comunicarse con el servidor Meta: " + e.getMessage(), e);
        }
    }
}
