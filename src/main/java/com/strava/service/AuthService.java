package com.strava.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.strava.dao.SesionRepository;
import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.dao.UserRepository;
import com.strava.dto.UsuarioDTO;
import com.strava.entity.Sesion;
import com.strava.entity.Usuario;
import com.strava.factory.AuthenticationFactory;
import com.strava.server.IMetaGateway;

import ch.qos.logback.classic.Logger;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuthService.class);
    
    private final UserRepository userRepository;
    private final SesionRepository sesionRepository;

    public AuthService(UserRepository userRepository, SesionRepository sesionRepository) {
        this.userRepository = userRepository;
        this.sesionRepository = sesionRepository;
    }


    public Optional<Usuario> encontraUser(UsuarioDTO usuario) {
        return userRepository.findByEmail(usuario.getEmail());
    }

    public Optional<String> login(UsuarioDTO user) {
        try {
            logger.info("Procesando login para usuario: {} con método: {}", 
                user.getEmail(), user.getTipoAutentication());

            // Creacion de gateway apropiado
            IMetaGateway gateway = AuthenticationFactory.creacionGateways(user.getTipoAutentication());
            
            // Intentar login
            String loginSuccess = gateway.login(user);
            
            if (loginSuccess.equals("Valido")) {
                logger.info("Login exitoso para usuario: {}", user.getEmail());
                String token = generateToken();
                return Optional.of(token);
            } else {
                logger.warn("Login fallido para usuario: {}", user.getEmail());
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Error en el proceso de login: {}", e.getMessage());
            return Optional.empty();
        }
    }

    // Método para registrar sesiones de entrenamiento
    public String crearSesionEntrenamiento(String email, String distancia, double duration, String type) {
        logger.info("Creando sesión de entrenamiento para usuario: {}", email);
        
        try {
            // Buscar el usuario por email
            Usuario usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con email: " + email));

            // Crear nueva sesión
            Sesion sesion = new Sesion();
            sesion.setTitulo("Sesión de " + type);
            sesion.setDeporte(TipoDeporte.valueOf(type));
            sesion.setDistancia(TipoDistancia.valueOf(distancia));
            sesion.setFechaInicio(LocalDate.now());
            sesion.setHoraInicio(LocalTime.now());
            sesion.setDuracion(duration);
            sesion.setUsuario(usuario);

            // Guardar la sesión en la base de datos
            sesion = sesionRepository.save(sesion);
            logger.info("Sesión guardada exitosamente en BD con ID: {}", sesion.getId());

            return String.valueOf(sesion.getId());

        } catch (EntityNotFoundException e) {
            logger.error("Usuario no encontrado: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error al crear sesión de entrenamiento: {}", e.getMessage());
            return null;
        }
    }

    private String generateToken() {
        return Long.toHexString(System.currentTimeMillis());
    }
    
    
    
}
