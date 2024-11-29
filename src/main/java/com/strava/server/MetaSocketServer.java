package com.strava.server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class MetaSocketServer {

    private static final int PORT = 9090;
    private static final ConcurrentHashMap<String, String> userDatabase = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Meta Socket Server escuchando en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request = in.readLine();
            String[] parts = request.split(" ");
            String command = parts[0];

            switch (command) {
                case "checkEmail":
                    String email = parts[1];
                    out.println(userDatabase.containsKey(email));
                    break;
                case "validateCredentials":
                    email = parts[1];
                    String password = parts[2];
                    out.println(userDatabase.getOrDefault(email, "").equals(password));
                    break;
                case "register":
                    email = parts[1];
                    password = parts[2];
                    userDatabase.put(email, password);
                    out.println("OK");
                    break;
                default:
                    out.println("Invalid Command");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

