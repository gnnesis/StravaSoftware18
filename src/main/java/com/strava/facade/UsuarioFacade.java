package com.strava.facade;


import org.springframework.stereotype.Component;

import com.strava.service.UsuarioService;
import com.strava.dto.UsuarioDTO;

@Component
public class UsuarioFacade {

    private final UsuarioService usuarioService;

    public UsuarioFacade(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioService.registrarUsuario(usuarioDTO);
    }

    public String iniciarSesion(String email, String password) {
        return usuarioService.iniciarSesion(email, password);
    }

    public void cerrarSesion(String token) {
        usuarioService.cerrarSesion(token);
    }
}
