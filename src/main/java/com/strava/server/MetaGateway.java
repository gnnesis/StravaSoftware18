package com.strava.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strava.dao.SesionRepository;
import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.dao.UserRepository;
import com.strava.entity.Sesion;
import com.strava.entity.Usuario;

@Service
public class MetaGateway implements IMetaGateway {
    private String serverIP;
    private int serverPort;
    private static String DELIMITER = "#";

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private UserRepository userRepository;

    public MetaGateway() {
        this.serverIP = "127.0.0.1";
        this.serverPort = 9500;
    }

    public String crearSesionEntrenamiento(String email, String distancia, double duration, String type) {
        System.out.println("creando sesion de entrenamiento...");
        
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

                Optional<Usuario> usuarioOpt = userRepository.findByEmail(email);
                if (usuarioOpt.isPresent()) {
                    Usuario usuario = usuarioOpt.get();
                    
                    Sesion sesion = new Sesion();
                    sesion.setId(sessionId);
                    sesion.setTitulo("Sesión de " + type);
                    sesion.setDeporte(TipoDeporte.valueOf(type));
                    sesion.setDistancia(TipoDistancia.valueOf(distancia));
                    sesion.setFechaInicio(fechaInicio);
                    sesion.setHoraInicio(horaInicio);
                    sesion.setDuracion(duration);
                    sesion.setUsuario(usuario);

                    sesionRepository.save(sesion);
                    System.out.println("Sesión guardada en BD con ID: " + sessionId);
                    
                    return String.valueOf(sessionId);
                } else {
                    System.err.println("Usuario no encontrado con email: " + email);
                }
            } catch (Exception e) {
                System.err.println("Error al guardar la sesión en BD: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    
    
    


	@Override
	public String creacionSesion(long id, String titulo, TipoDeporte deporte, TipoDistancia distancia,
			LocalDate fechaInicio, LocalTime horaInicio, double duracion, Usuario user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
	    MetaGateway client = new MetaGateway();
	    
	    String sessionId = client.crearSesionEntrenamiento(
	        "test@example.com",
	        "TIEMPO",
	        10.5,
	        "RUNNING"
	    );
	    
	    System.out.println("Sesión creada con ID: " + sessionId);
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


