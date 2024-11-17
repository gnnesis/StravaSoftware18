package com.strava.facade;


import org.springframework.stereotype.Component;

import com.ejemplo.service.UsuarioService;
import com.strava.dto.UsuarioDTO;

@Component
public class UsuarioFacade {

    private final UsuarioService usuarioService;

    public UsuarioFacade(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar un nuevo usuario
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioService.registrarUsuario(usuarioDTO); // Llama al servicio para registrar el usuario
    }

    // Iniciar sesión
    public String iniciarSesion(String email, String password) {
        return usuarioService.iniciarSesion(email, password); // Llama al servicio para iniciar sesión y obtener token
    }

    // Cerrar sesión
    public void cerrarSesion(String token) {
        usuarioService.cerrarSesion(token); // Llama al servicio para simular el cierre de sesión
    }
}
