package com.strava.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.strava.dto.UsuarioDTO;

import ch.qos.logback.classic.Logger;

@Service
public class MetaGateway implements IMetaGateway {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MetaGateway.class);
    private static final String DELIMITER = "#";
    private final String serverIP;
    private final int serverPort;

    public MetaGateway() {
        this.serverIP = "127.0.0.1";
        this.serverPort = 9500;
    }

    @Override
    public String login(UsuarioDTO usuario) {
        logger.info("Iniciando login via Meta para usuario: {}", usuario.getEmail());
        
        String response = sendRequest("LOGIN", usuario.getEmail(), usuario.getPassword());
        
        boolean success = response != null && response.equals("LOGIN_OK"+usuario.getEmail());
        if (success) {
            logger.info("Login exitoso via Meta");
            return "Valido";
        } else {
            logger.warn("Login fallido via Meta");
            return "Invalido";
        }
        
        
    }

    private String sendRequest(String requestType, String... params) {
        String request = requestType + DELIMITER + String.join(DELIMITER, params);
        String response = "ERROR";

        logger.info("Conectando con servidor Meta...");
        try (Socket socket = new Socket(serverIP, serverPort)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            logger.debug("Enviando: {}", request);
            out.writeUTF(request);
            out.flush();

            response = in.readUTF();
            logger.debug("Respuesta recibida: {}", response);
            
            return response;
        } catch (IOException e) {
            logger.error("Error en la conexión con servidor Meta: {}", e.getMessage());
        }
        
        return response;
    }
}