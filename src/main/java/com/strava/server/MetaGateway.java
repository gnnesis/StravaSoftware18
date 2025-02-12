package com.strava.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strava.dao.SesionRepository;
import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.dao.UserRepository;
import com.strava.entity.Sesion;
import com.strava.entity.Usuario;

import jakarta.persistence.EntityNotFoundException;
@Service
public class MetaGateway implements IMetaGateway {
    private static final Logger logger = LoggerFactory.getLogger(MetaGateway.class);
    private String serverIP;
    private int serverPort;
    private static final String DELIMITER = "#";

    private final SesionRepository sesionRepository;
    private final UserRepository userRepository;

    @Autowired
    public MetaGateway(SesionRepository sesionRepository, UserRepository userRepository) {
        this.sesionRepository = sesionRepository;
        this.userRepository = userRepository;
        this.serverIP = "127.0.0.1";
        this.serverPort = 9500;
    }

    @Transactional
    public String crearSesionEntrenamiento(String email, String distancia, double duration, String type) {
        logger.info("Creando sesión de entrenamiento para usuario: {}", email);
        
        String response = sendRequest("CREAR_SESION_ENTRENAMIENTO", 
                                   email,
                                   distancia,
                                   String.valueOf(duration),
                                   type);
        
        if (response != null && response.startsWith("SESION_CREADA#")) {
            try {
                String[] parts = response.split("#");
                long sessionId = Long.parseLong(parts[1]);
                LocalDate fechaInicio = LocalDate.parse(parts[2]);
                LocalTime horaInicio = LocalTime.parse(parts[3]);

                Usuario usuario = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con email: " + email));
                    
                Sesion sesion = new Sesion();
                sesion.setId(sessionId);
                sesion.setTitulo("Sesión de " + type);
                sesion.setDeporte(TipoDeporte.valueOf(type));
                sesion.setDistancia(TipoDistancia.valueOf(distancia));
                sesion.setFechaInicio(fechaInicio);
                sesion.setHoraInicio(horaInicio);
                sesion.setDuracion(duration);
                sesion.setUsuario(usuario);

                sesion = sesionRepository.save(sesion);
                logger.info("Sesión guardada exitosamente en BD con ID: {}", sessionId);
                
                return String.valueOf(sessionId);
            } catch (Exception e) {
                logger.error("Error al guardar la sesión en BD: {}", e.getMessage(), e);
                throw new RuntimeException("Error al crear la sesión de entrenamiento", e);
            }
        }
        
        logger.error("No se pudo crear la sesión de entrenamiento");
        return null;
    }
    
    
    
    


	@Override
	public String creacionSesion(long id, String titulo, TipoDeporte deporte, TipoDistancia distancia,
			LocalDate fechaInicio, LocalTime horaInicio, double duracion, Usuario user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	 // Método para enviar solicitudes al servidor y recibir respuesta
    public String sendRequest(String requestType, String... params) {
        String request = requestType + DELIMITER + String.join(DELIMITER, params);
        String response = "ERROR";

        System.out.println("Intentando conectar con el servidor");
        try (Socket socket = new Socket(serverIP, serverPort)) {
            // Crear streams después de establecer la conexión
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Enviar solicitud
            System.out.println("➡ Enviando: " + request);
            out.writeUTF(request);
            out.flush(); // Importante: asegurarse de que los datos se envíen

            // Esperar y leer respuesta
            response = in.readUTF();
            System.out.println("⬅ Respuesta: " + response);

            return response;
        } catch (IOException e) {
            System.err.println(" # MetaGateway: Error en la conexión - " + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

}
