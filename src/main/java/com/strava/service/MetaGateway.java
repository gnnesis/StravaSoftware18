package com.strava.service;

//import org.springframework.stereotype.Component;
//import java.io.*;
//import java.net.Socket;
//
//@Component
//public class MetaGateway {
//
//    private static final String META_SERVER_HOST = "localhost"; // Cambia si el server está en otra IP
//    private static final int META_SERVER_PORT = 12345;          // Puerto donde escucha socket-meta-server
//
//    // Verificar si el email está registrado
//    public boolean checkEmail(String email) {
//        String request = "CHECK_EMAIL," + email;
//        String response = sendRequest(request);
//        return "EMAIL_FOUND".equals(response);
//    }
//
//    // Validar login con email y password
//    public boolean login(String email, String password) {
//        String request = "VALIDATE_LOGIN," + email + "," + password;
//        String response = sendRequest(request);
//        return "LOGIN_SUCCESS".equals(response);
//    }
//
//    // Método para enviar una solicitud al servidor Meta
//    private String sendRequest(String request) {
//        try (Socket socket = new Socket(META_SERVER_HOST, META_SERVER_PORT);
//             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//
//            // Enviar solicitud
//            writer.write(request);
//            writer.newLine();
//            writer.flush();
//
//            // Leer respuesta
//            return reader.readLine();
//
//        } catch (IOException e) {
//            throw new RuntimeException("Error al comunicarse con el servidor Meta: " + e.getMessage(), e);
//        }
//    }
//}


import org.springframework.stereotype.Component;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Scanner;

@Component
public class MetaGateway {

    private String serverIP;
    private int serverPort;
    private static final String DELIMITER = ",";

    public MetaGateway(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public String sendRequest(String requestType, String... params) {
        String request = requestType + DELIMITER + String.join(DELIMITER, params);
        String response = null;
        StringTokenizer tokenizer = null;

        try (Socket socket = new Socket(serverIP, serverPort);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            // Enviar solicitud
            out.writeUTF(request);
            System.out.println(" - Enviando solicitud a " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + " -> " + request);

            // Leer respuesta
            response = in.readUTF();
            System.out.println(" - Recibiendo respuesta de " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + " -> " + response);
            tokenizer = new StringTokenizer(response, DELIMITER);

        } catch (UnknownHostException e) {
            System.err.println(" # MetaGateway: Error de conexión: " + e.getMessage());
        } catch (EOFException e) {
            System.err.println(" # MetaGateway: Fin de flujo inesperado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println(" # MetaGateway: Error de E/S: " + e.getMessage());
        }

        return (tokenizer != null && tokenizer.nextToken().equals("OK")) ? tokenizer.nextToken() : "ERROR";
    }

    public boolean checkEmail(String email) {
        return "EMAIL_FOUND".equals(sendRequest("CHECK_EMAIL", email));
    }

    public boolean login(String email, String password) {
        return "LOGIN_SUCCESS".equals(sendRequest("VALIDATE_LOGIN", email, password));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println(" # Uso: MetaGateway [SERVER IP] [PORT]");
            System.exit(1);
        }

        MetaGateway client = new MetaGateway(args[0], Integer.parseInt(args[1]));
        Scanner scanner = new Scanner(System.in);
        String email;
        String password;

        while (true) {
            System.out.print(" - Ingrese el email o Q para salir:\n - ");
            email = scanner.nextLine();

            if (email.equalsIgnoreCase("Q")) {
                break;
            }

            System.out.print(" - Ingrese la contraseña:\n - ");
            password = scanner.nextLine();

            boolean success = client.login(email, password);
            System.out.println(success ? " -> Inicio de sesión exitoso" : " -> Error de autenticación");
        }

        scanner.close();
    }
}