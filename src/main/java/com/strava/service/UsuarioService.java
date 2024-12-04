package com.strava.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.strava.dao.TipoAutentication;
import com.strava.entity.Usuario;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<String> blacklistTokens = new ArrayList<>();

    public Usuario registrarUsuario(String email, String nombre, Date fechaNacimiento,
                                    Double peso, Double altura, Integer frecuenciaMaxima,
                                    Integer frecuenciaReposo, String password, TipoAutentication tipoAutentication) {

        Optional<Usuario> existente = usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (existente.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado: " + email);
        }

        Usuario nuevoUsuario = new Usuario(email, nombre, fechaNacimiento, peso, altura,
                                            frecuenciaMaxima, frecuenciaReposo, password, tipoAutentication);
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    public Usuario autenticarUsuario(String email, String password) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }

        return null;
    }

    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un usuario con el correo: " + email));
    }

    public void invalidarToken(String token) {
        blacklistTokens.add(token);
    }

    public boolean esTokenValido(String token) {
        return !blacklistTokens.contains(token);
    }
}
