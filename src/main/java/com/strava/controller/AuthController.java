package com.strava.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.strava.dto.LoginDTO;
import com.strava.dto.RegistroDTO;
import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;
import com.strava.server.MetaGateway;
import com.strava.service.AuthService;
import com.strava.service.UsuarioService;
import com.strava.utils.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.strava.service.UsuarioService;


@RequestMapping("/auth")
@RestController
public class AuthController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
	private AuthService authService;
	private UsuarioService usuarioService;
    
    @Autowired
    public AuthController(AuthService authService, UsuarioService usuarioService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
    }
    

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        try {
            usuarioService.registrarUsuario(registroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el registro: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO user) {
        logger.info("Recibida solicitud de login para usuario: {}", user.getEmail());
        
        try {
            // Primero verificamos si el usuario existe
            Optional<Usuario> userExists = authService.encontraUser(user);
            //si no existe
            if (userExists.isEmpty()) {
                logger.warn("Usuario no encontrado: {}", user.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario no encontrado");
            }
            // Intentamos el login
            Optional<String> token = authService.login(user);
            if (token.isPresent()) {
                logger.info("Login exitoso para usuario: {}", user.getEmail());
                return new ResponseEntity<>(token.get(), HttpStatus.OK);
            } else {
                logger.warn("Login fallido para usuario: {}", user.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
            }
        } catch (Exception e) {
            logger.error("Error durante el login: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error en el proceso de login");
        }
    
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay sesión activa");
        }

        try {
            TokenUtil.validarToken(token); // Verificar si el token es válido
            return ResponseEntity.ok("Logout exitoso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
    }

    // Verificar email utilizando Meta
    @GetMapping("/verify-email")
    public ResponseEntity<String> verificarEmail(@RequestParam String email) {
        boolean emailValido = usuarioService.verificarEmail(email);
        if (emailValido) {
            return ResponseEntity.ok("El email está registrado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El email no está registrado.");
        }
    }

    // Validar login utilizando Meta
    @PostMapping("/validate-login")
    public ResponseEntity<String> validateLogin(@RequestBody LoginRequest loginRequest) {
        // Crear el request para enviar al servidor
        String request = "validate-login," + loginRequest.getEmail() + "," + loginRequest.getPassword();
        // Llamar a sendRequest usando la instancia inyectada de MetaSocketClient
        String response = metaSocketClient.sendRequest("VALIDATE_LOGIN", loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
