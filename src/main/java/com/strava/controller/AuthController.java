package com.strava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;
import com.strava.service.UsuarioService;
import com.strava.utils.TokenUtil;
import com.strava.server.MetaSocketClient;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        // Registrar al usuario
        Usuario usuario = usuarioService.registrarUsuario(
                usuarioDTO.getEmail(),
                usuarioDTO.getNombre(),
                usuarioDTO.getFechaNacimiento(),
                usuarioDTO.getPeso(),
                usuarioDTO.getAltura(),
                usuarioDTO.getFrecuenciaCardiacaMaxima(),
                usuarioDTO.getFrecuenciaCardiacaReposo(),
                usuarioDTO.getPassword(),
                usuarioDTO.getTipoAutentication());
        
        // Generar el token para el usuario registrado
        String token = TokenUtil.generarToken(usuario.getEmail());
        
        // Devolver la respuesta con el usuario y el token
        return ResponseEntity.ok()
                             .header("Authorization", token) // Ajuste aquí para incluir el header correctamente
                             .body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.autenticarUsuario(usuarioDTO.getEmail(), usuarioDTO.getPassword());

        // Si no hay usuario
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        // Generar un token personalizado
        String token = TokenUtil.generarToken(usuario.getEmail());
        return ResponseEntity.ok("Token: " + token);
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
    public ResponseEntity<String> verifyEmail(@RequestBody String email) {
        String request = "verify-email," + email;
        String response = MetaSocketClient.sendCommand(request);
        return ResponseEntity.ok(response);
    }


    //Validar login utilizando Meta
    @PostMapping("/validate-login")
    public ResponseEntity<String> validateLogin(@RequestBody LoginRequest loginRequest) {
        String request = "validate-login," + loginRequest.getEmail() + "," + loginRequest.getPassword();
        String response = MetaSocketClient.sendCommand(request);
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
