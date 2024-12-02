package com.strava.service;
import java.sql.Date;

import org.springframework.stereotype.Service;

import com.strava.entity.TipoAutentication;
import com.strava.entity.Usuario;
import com.strava.repository.UserRepository;

	@Service
	public class GoogleLoginService {
	    private final UserRepository usuarioRepository;

	    public GoogleLoginService(UserRepository usuarioRepository) {
	        this.usuarioRepository = usuarioRepository;
	    }

	    public boolean isEmailRegistered(String email) {
	        return usuarioRepository.existsById(email);
	    }

	    public boolean validateCredentials(String email, String password) {
	        Usuario user = usuarioRepository.findByEmailAndPassword(email, password);
	        return user != null;
	    }

	    public void register(String email, String password, String nombre,  Date fechaNacimiento, Double peso, 
	                         Double altura, Integer frecuenciaCardiacaMaxima, Integer frecuenciaCardiacaReposo, String password1) {
	        if (!isEmailRegistered(email)) {
	            Usuario user = new Usuario(email, nombre, fechaNacimiento, peso, altura, 
	                                        frecuenciaCardiacaMaxima, frecuenciaCardiacaReposo, password1, TipoAutentication.Google);
	            usuarioRepository.save(user);
	        }
	    }

}
