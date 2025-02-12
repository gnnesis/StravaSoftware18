package com.strava.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strava.dao.TipoAutentication;
import com.strava.dao.UserRepository;
import com.strava.dto.LoginDTO;
import com.strava.dto.RegistroDTO;
import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;
import com.strava.server.MetaGateway;
import com.strava.utils.TokenUtil;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<String> blacklistTokens = new ArrayList<>();

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private MetaGateway mg;
    
    public void registrarUsuario(UsuarioDTO u) {

       Optional<Usuario> existente = usuarioRepository.findById(u.getEmail());
       
       //sis ese
       if (existente.isPresent()) {
    	  System.out.println("ese email ya existe en nuestra BD");
		
	}else {
		 // Convertimos el DTO a una entidad de usuario
	    Usuario nuevoUsuario = new Usuario(
	            u.getEmail(), 
	            u.getNombre(), 
	            u.getFechaNacimiento(), 
	            u.getPeso(), 
	            u.getAltura(), 
	            u.getFrecuenciaCardiacaMaxima(), 
	            u.getFrecuenciaCardiacaReposo(), 
	            u.getPassword(),
	            u.getTipoAutentication()
	    );

	  System.out.println(nuevoUsuario.toString());
	  usuarioRepository.save(nuevoUsuario);
	}
       
       
    }
    
    
    

    
    
    
    public void registrarUsuario(RegistroDTO registroDTO) {
        boolean emailExiste = mg.checkEmail(registroDTO.getEmail());
        if (emailExiste) {
            throw new RuntimeException("El email ya está registrado en Meta.");
        }

        Usuario usuario = new Usuario(registroDTO.getEmail(), registroDTO.getPassword(), null, null, null, null, null, registroDTO.getNombre(), null);
        usuarioRepository.save(usuario);
    }
    
    public String login(LoginDTO loginDTO) {
        boolean loginValido = mg.login(loginDTO.getEmail(), loginDTO.getPassword());
        if (!loginValido) {
            throw new RuntimeException("Credenciales inválidas.");
        }

        return TokenUtil.generarToken(loginDTO.getEmail());
    }
    
    public boolean verificarEmail(String email) {
        return mg.checkEmail(email);
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