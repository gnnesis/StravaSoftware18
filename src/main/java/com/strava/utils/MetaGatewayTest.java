package com.strava.utils;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.strava.dao.TipoAutentication;
import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.dao.UserRepository;
import com.strava.dto.UsuarioDTO;
import com.strava.entity.Sesion;
import com.strava.entity.Usuario;
import com.strava.server.MetaGateway;

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
            // Crear usuario de prueba
            Usuario testUser = new Usuario(
                "test@example.com",
                "Usuario Test",
                new Date(System.currentTimeMillis()),
                "password123",
                TipoAutentication.META
            );
            
            userRepository.save(testUser);
            System.out.println("✅ Usuario de prueba creado");

            // Probar login MG
            UsuarioDTO loginUser = new UsuarioDTO();
            loginUser.setEmail("test@example.com");
            loginUser.setPassword("password123");
            loginUser.setTipoAutentication(TipoAutentication.META);

            var loginResult = metaGateway.login(loginUser);
            
            if (loginResult == true) {
                System.out.println("✅ Login exitoso, token generado");
            } else {
                System.err.println("❌ Login fallido");
            }

            // Simular creación de sesión de entrenamiento (sin conectarse a MetaGateway)
            System.out.println("✅ Simulando la creación de sesión de entrenamiento...");
            
            Sesion sesion = new Sesion();
            sesion.setId(12345L); // ID simulado
            sesion.setTitulo("Sesión de prueba");
            sesion.setDeporte(TipoDeporte.RUNNING);
            sesion.setDistancia(TipoDistancia.TIEMPO);
            sesion.setFechaInicio(Date.valueOf("2025-02-13").toLocalDate());
            sesion.setHoraInicio(java.time.LocalTime.now());
            sesion.setDuracion(10.5);
            sesion.setUsuario(testUser);

            System.out.println("✅ Sesión de entrenamiento simulada con ID: " + sesion.getId());
            
        } catch (Exception e) {
            System.err.println("❌ Error en las pruebas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
