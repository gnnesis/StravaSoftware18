package com.strava.server;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class MetaSocketServer {
    private static final int PORT = 8080;
    private static HashMap<String, String> users = new HashMap<>(); // email -> password

    public static void main(String[] args) {
        // Inicializar usuarios en memoria
        users.put("user1@example.com", "password123");
        users.put("user2@example.com", "securepassword");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor Meta escuchando en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión recibida de: " + clientSocket.getInetAddress());

                // Procesar la solicitud del cliente
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String request = in.readLine();
                System.out.println("Solicitud recibida: " + request);

                String response = processRequest(request);
                out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String processRequest(String request) {
            try {
                String[] parts = request.split(",");
                String operation = parts[0].trim();
                String email = parts[1].trim();
                switch (operation) {
                    case "verify-email":
                        boolean isRegistered = users.containsKey(email);
                        return "{\"registered\": " + isRegistered + "}";
                    case "validate-login":
                        String password = parts[2].trim();
                        boolean valid = password.equals(users.get(email));
                        return "{\"valid\": " + valid + ", \"message\": \"" + (valid ? "Authentication successful" : "Invalid credentials") + "\"}";
                    default:
                        return "{\"error\": \"Unknown operation\"}";
                }
            } catch (Exception e) {
                return "{\"error\": \"Invalid request format\"}";
            }
        }
    }
}
