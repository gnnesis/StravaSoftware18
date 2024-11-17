package com.strava.controller;

import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;
import com.strava.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrarUsuario(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.registrarUsuario(usuarioDTO.getEmail(), usuarioDTO.getNombre(), "google");
        return ResponseEntity.ok(usuario);
    }
}
