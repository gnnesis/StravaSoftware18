package com.strava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.strava.dto.LoginDTO;
import com.strava.dto.RegistroDTO;
import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;
import com.strava.service.UsuarioService;
import com.strava.utils.TokenUtil;
import com.strava.server.MetaGateway;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private MetaGateway metaSocketClient; // Inyección de MetaSocketClient

    public AuthController(UsuarioService usuarioService) {
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
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = usuarioService.login(loginDTO);
            return ResponseEntity.ok("Login exitoso. Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en el login: " + e.getMessage());
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
