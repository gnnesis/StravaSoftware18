package com.strava.server;

import org.springframework.stereotype.Service;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

@Service
public class MetaSocketClient {

    private String serverIP;
    private int serverPort;
    private static final String DELIMITER = "#";

    // Constructor para inicializar la IP y el puerto del servidor
    public MetaSocketClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    // Método para enviar solicitudes al servidor
    public String sendRequest(String requestType, String... params) {
        String request = requestType + DELIMITER + String.join(DELIMITER, params);
        String response = null;
        StringTokenizer tokenizer = null;

        Socket socket = null;
        try {
            // Establecer el socket con timeout para evitar bloqueos indefinidos
            socket = new Socket();
            socket.connect(new InetSocketAddress(serverIP, serverPort), 5000); // Timeout de 5 segundos

            System.out.println("Conexión establecida con el servidor: " + serverIP + ":" + serverPort); // Log de conexión

            try (DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                // Enviar la solicitud
                System.out.println("Enviando solicitud: " + request); // Log de la solicitud enviada
                out.writeUTF(request);
                System.out.println("Solicitud enviada, esperando respuesta...");

                // Leer respuesta
                response = in.readUTF();
                System.out.println("Respuesta recibida: " + response); // Log de la respuesta recibida

                // Procesar la respuesta
                tokenizer = new StringTokenizer(response, DELIMITER);

            }

        } catch (UnknownHostException e) {
            System.err.println(" # MetaGateway: Error de conexión: " + e.getMessage());
        } catch (EOFException e) {
            System.err.println(" # MetaGateway: Fin de flujo inesperado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println(" # MetaGateway: Error de E/S: " + e.getMessage());
        } finally {
            // Asegurarse de que el socket se cierre correctamente
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                    System.out.println("Socket cerrado correctamente.");
                } catch (IOException e) {
                    System.err.println(" # MetaGateway: Error al cerrar el socket: " + e.getMessage());
                }
            }
        }

        // Verificar si la respuesta contiene un token "OK" y devolver el siguiente token
        if (tokenizer != null && tokenizer.hasMoreTokens()) {
            String status = tokenizer.nextToken();
            return status.equals("OK") ? tokenizer.nextToken() : "ERROR";
        }

        return "ERROR"; // En caso de que no haya tokens o no sea 'OK'
    }

    // Método para comprobar si el correo electrónico ya existe
    public boolean checkEmail(String email) {
        return "EMAIL_FOUND".equals(sendRequest("CHECK_EMAIL", email));
    }

    // Método para realizar el login con email y contraseña
    public boolean login(String email, String password) {
        return "LOGIN_SUCCESS".equals(sendRequest("VALIDATE_LOGIN", email, password));
    }
}
