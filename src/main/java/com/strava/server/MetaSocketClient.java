package com.strava.server;

import java.io.*;
import java.net.*;

public class MetaSocketClient {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static boolean checkEmail(String email) {
        return sendCommand("checkEmail " + email).equalsIgnoreCase("true");
    }

    public static boolean validateCredentials(String email, String password) {
        return sendCommand("validateCredentials " + email + " " + password).equalsIgnoreCase("true");
    }

    public static String register(String email, String password) {
        return sendCommand("register " + email + " " + password);
    }

    public static String sendCommand(String command) {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println(command);
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}

