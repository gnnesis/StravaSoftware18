package com.strava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;
import com.strava.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private Usuario usuarioAutenticado;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.registrarUsuario(usuarioDTO.getEmail(), usuarioDTO.getNombre(), usuarioDTO.getFechaNacimiento(), usuarioDTO.getPeso(), usuarioDTO.getAltura(), usuarioDTO.getFrecuenciaCardiacaMaxima(), usuarioDTO.getFrecuenciaCardiacaReposo(), usuarioDTO.getPassword());
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.autenticarUsuario(usuarioDTO.getEmail(), usuarioDTO.getPassword());
        
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        usuarioAutenticado = usuario;
          return ResponseEntity.ok(usuarioDTO.getEmail() + " ha iniciado sesión");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        if (usuarioAutenticado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay sesión activa");
        }

        usuarioAutenticado = null;

        return ResponseEntity.ok("Logout exitoso");
    }
}
