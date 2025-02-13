package com.strava.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.strava.server.MetaGateway;
import com.strava.entity.Usuario;
import com.strava.dao.UserRepository;
import java.sql.Date;
import com.strava.dao.TipoAutentication;

@Component
public class MetaGatewayTest implements CommandLineRunner {
    
    private final MetaGateway metaGateway;
    private final UserRepository userRepository;
    
    public MetaGatewayTest(MetaGateway metaGateway, UserRepository userRepository) {
        this.metaGateway = metaGateway;
        this.userRepository = userRepository;
    }
    
    @Override
    public void run(String... args) {
        try {
            // Primero, crear un usuario de prueba
            Usuario testUser = new Usuario(
                "test@example.com",
                "Usuario Test",
                new Date(System.currentTimeMillis()),
                "password123",
                TipoAutentication.META
            );
            
            userRepository.save(testUser);
            System.out.println("Usuario de prueba creado");
            
            // Ahora crear una sesión de entrenamiento
            String sessionId = metaGateway.crearSesionEntrenamiento(
                "test@example.com",
                "TIEMPO",
                10.5,
                "RUNNING"
            );
            
            System.out.println("Sesión creada con ID: " + sessionId);
            
        } catch (Exception e) {
            System.err.println("Error en las pruebas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
