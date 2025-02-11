package com.strava.server;



import org.springframework.stereotype.Service;

import java.io.*;

import java.net.*;



@Service

public class MetaSocketClient {



    private String serverIP;

    private int serverPort;

    private static final String DELIMITER = "#";



    // Constructor

    public MetaSocketClient(String serverIP, int serverPort) {

        this.serverIP = serverIP;

        this.serverPort = serverPort;

    }



    // Método para enviar solicitudes al servidor y recibir respuesta

    public String sendRequest(String requestType, String... params) {

        String request = requestType + DELIMITER + String.join(DELIMITER, params);

        String response = "ERROR";



        try (Socket socket = new Socket(serverIP, serverPort);

             DataOutputStream out = new DataOutputStream(socket.getOutputStream());

             DataInputStream in = new DataInputStream(socket.getInputStream())) {



            // Enviar solicitud

            System.out.println("➡ Enviando: " + request);

            out.writeUTF(request);

            out.flush();



            // Leer respuesta

            response = in.readUTF();

            System.out.println("⬅ Respuesta: " + response);



        } catch (IOException e) {

            System.err.println(" # MetaGateway: Error en la conexión - " + e.getMessage());

        }



        return response;

    }



    // Método para registrar un usuario

    public boolean register(String email, String password) {

        return "OK".equals(sendRequest("REGISTER", email, password));

    }



    // Método para comprobar si el correo existe

    public boolean checkEmail(String email) {

        return "EMAIL_FOUND".equals(sendRequest("CHECK_EMAIL", email));

    }



    // Método para iniciar sesión

    public boolean login(String email, String password) {

        return "LOGIN_SUCCESS".equals(sendRequest("VALIDATE_LOGIN", email, password));

    }



    // Método para crear una sesión de entrenamiento

    public boolean createTrainingSession(String email, double distance, String duration, String type) {

        return "SESSION_CREATED".equals(sendRequest("CREATE_TRAINING_SESSION", email, String.valueOf(distance), duration, type));

    }



    // Método para obtener las sesiones del usuario

    public String getMyTrainingSessions(String email) {

        return sendRequest("GET_MY_TRAINING_SESSIONS", email);

    }



    // Método de prueba principal

    public static void main(String[] args) {

        MetaSocketClient client = new MetaSocketClient("localhost", 12345);



        // Registrar usuario

        boolean isRegistered = client.register("test@example.com", "password123");

        System.out.println("Registro exitoso: " + isRegistered);

        // Intentar login

        boolean isLoggedIn = client.login("test@example.com", "password123");

        System.out.println("Login exitoso: " + isLoggedIn);



        // Crear sesión de entrenamiento

        boolean sessionCreated = client.createTrainingSession("test@example.com", 10.5, "00:45:30", "Running");

        System.out.println("Sesión de entrenamiento creada: " + sessionCreated);



        // Obtener sesiones del usuario

        String sessions = client.getMyTrainingSessions("test@example.com");

        System.out.println("Sesiones del usuario: " + sessions);

    }

}