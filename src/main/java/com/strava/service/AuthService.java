package com.strava.service;

import org.springframework.stereotype.Service;

import com.strava.dao.UserRepository;
import com.strava.dto.SesionDTO;
import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;

@Service
public class AuthService {
	
	private final UserRepository repositorio;
	
	Usuario u = new Usuario();
	
	
	public AuthService(UserRepository repositorio) {
		this.repositorio= repositorio;
	}
	
//	   // Método de registro
//    public void register(UsuarioDTO userDto) {
//        if (repositorio.existsById(userDto.getEmail())) {
//            throw new RuntimeException("Email already exists");
//        }
//
//        UsuarioDTO user = new UsuarioDTO();
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword()); // Encriptación de la contraseña
//        repositorio.save(user);
//    }
		
	 // Método de login
    public boolean login(UsuarioDTO user) {
         u = repositorio.findById(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (u.getPassword().equals(user.getPassword())) {
            return true; // Login exitoso
        }
        return false; // Login fallido
    }

}
