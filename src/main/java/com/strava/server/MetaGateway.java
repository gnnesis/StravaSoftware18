package com.strava.server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.entity.Sesion;
import com.strava.entity.Usuario;




@Service

public class MetaGateway implements IMetaGateway{

	private String serverIP;
	private int  serverPort;
	private static String DELIMITER = "#";

    // Constructor
    public MetaGateway(String servIP, int servPort) {
    	serverIP = servIP;
		serverPort = servPort;
    }
   
    public MetaGateway() {
    }
    
    
    
    
    // MAIN
    public static void main(String[] args) {
        MetaGateway client = new MetaGateway("127.0.0.1", 9500);



        // Registrar usuario
/*        boolean isRegistered = client.register("test@example.com", "password123");
        System.out.println("Registro exitoso: " + isRegistered);

        // Intentar login
        boolean isLoggedIn = client.login("test@example.com", "password123");
        System.out.println("Login exitoso: " + isLoggedIn);
*/


        // Crear sesión de entrenamiento
        String sessionCreated = client.crearSesionEntrenamiento("test@example.com", TipoDistancia.TIEMPO.toString(), 10.5, TipoDeporte.RUNNING.toString());
        System.out.println("Sesión de entrenamiento creada: " + sessionCreated);


/*
       // Obtener sesiones del usuario
        String sessions = client.getMyTrainingSessions("test@example.com");
        System.out.println("Sesiones del usuario: " + sessions);
*/
        
        
        
    }

    // Método para enviar solicitudes al servidor y recibir respuesta
    public String sendRequest(String requestType, String... params) {
        String request = requestType + DELIMITER + String.join(DELIMITER, params);
        String response = "ERROR";

        System.out.println("Intentando conectar con el servidor");
        try (Socket socket = new Socket( serverIP,serverPort );
        		
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
            e.printStackTrace();
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
    public String crearSesionEntrenamiento(String email, String distancia, double duration, String type) {
    	
    	System.out.println("creando sesion de entrenamiento...");
    	
    	String response = sendRequest("CREAR_SESION_ENTRENAMIENTO", email,  distancia, String.valueOf(duration), type);
    	 // Si el servidor responde con un ID, lo devolvemos
        if (response.startsWith("SESION_CREADA#")) {
            return response.split("#")[1]; // Extrae el correo de la sesión
        }
        return null; // Fallo al crear la sesión
    
    }



//    // Método para obtener las sesiones del usuario
//    public String getMyTrainingSessions(String email) {
//        return sendRequest("GET_MY_TRAINING_SESSIONS", email);

//    }




	@Override
	public String creacionSesion(long id, String titulo, TipoDeporte deporte, TipoDistancia distancia,
			LocalDate fechaInicio, LocalTime horaInicio, double duracion, Usuario user) {
		Sesion s = new Sesion(id, titulo, deporte, distancia, fechaInicio, horaInicio, duracion, user);
		return null;
	}
}
