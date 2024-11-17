package com.strava.service;

import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    /**
     * Registra un nuevo usuario.
     *
     * @param email               Correo electrónico del usuario.
     * @param nombre              Nombre completo del usuario.
     * @param fechaNacimiento     Fecha de nacimiento del usuario (formato: "YYYY-MM-DD").
     * @param peso                Peso del usuario en kg (opcional).
     * @param altura              Altura del usuario en cm (opcional).
     * @param frecuenciaMaxima    Frecuencia cardíaca máxima en bpm (opcional).
     * @param frecuenciaReposo    Frecuencia cardíaca en reposo en bpm (opcional).
     * @return El usuario registrado.
     * @throws IllegalArgumentException Si el email ya está registrado.
     */
    public Usuario registrarUsuario(String email, String nombre, String fechaNacimiento,
            Double peso, Integer altura, Integer frecuenciaMaxima, Integer frecuenciaReposo, String password) {
		
		Usuario nuevoUsuario = new Usuario(email, nombre, fechaNacimiento, peso, altura, frecuenciaMaxima, frecuenciaReposo, password);
		usuarios.add(nuevoUsuario);
		return nuevoUsuario; 
    }

    /**
     * Obtiene un usuario por su email.
     *
     * @param email Correo electrónico del usuario.
     * @return El usuario encontrado.
     * @throws IllegalArgumentException Si el usuario no existe.
     */
    public Usuario obtenerUsuarioPorEmail(String email) {
        Optional<Usuario> usuarioOpt = usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        } else {
            throw new IllegalArgumentException("No se encontró un usuario con el correo: " + email);
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param email               Correo electrónico del usuario a actualizar.
     * @param nombre              Nuevo nombre completo del usuario (opcional).
     * @param peso                Nuevo peso del usuario en kg (opcional).
     * @param altura              Nueva altura del usuario en cm (opcional).
     * @param frecuenciaMaxima    Nueva frecuencia cardíaca máxima en bpm (opcional).
     * @param frecuenciaReposo    Nueva frecuencia cardíaca en reposo en bpm (opcional).
     * @return El usuario actualizado.
     * @throws IllegalArgumentException Si el usuario no existe.
     */
    public Usuario actualizarUsuario(String email, String nombre, Double peso, Integer altura,
                                     Integer frecuenciaMaxima, Integer frecuenciaReposo) {

        Usuario usuario = obtenerUsuarioPorEmail(email);

        if (nombre != null) usuario.setNombre(nombre);
        if (peso != null) usuario.setPeso(peso);
        if (altura != null) usuario.setAltura(altura);
        if (frecuenciaMaxima != null) usuario.setFrecuenciaMaxima(frecuenciaMaxima);
        if (frecuenciaReposo != null) usuario.setFrecuenciaReposo(frecuenciaReposo);

        return usuario;
    }

    /**
     * Elimina un usuario por su email.
     *
     * @param email Correo electrónico del usuario a eliminar.
     * @throws IllegalArgumentException Si el usuario no existe.
     */
    public void eliminarUsuario(String email) {
        Usuario usuario = obtenerUsuarioPorEmail(email);
        usuarios.remove(usuario);
    }

	public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public String iniciarSesion(String email, String password) {
		// TODO Auto-generated method stub
		return null;
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


	public void cerrarSesion(String token) {
		// TODO Auto-generated method stub
		
	}
}
