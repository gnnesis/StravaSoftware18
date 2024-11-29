package com.strava.service;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.strava.entity.Usuario;

@Service
public class GoogleLoginService {
/*CON BASE DE DATOS PERO NO FUNCIONA*/
//    private final UserRepository userRepository;
//
//    public GoogleLoginService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public boolean isEmailRegistered(String email) {
//        return userRepository.findById(email).isPresent();
//    }
//
//    public boolean validateCredentials(String email, String password) {
//        Optional<Usuario> user = userRepository.findById(email);
//        return user.isPresent() && user.get().getPassword().equals(password);
//    }
//
//    public void register(String email, String password) {
//        if (!isEmailRegistered(email)) {
//            userRepository.save(new Usuario(email, password, null, null, null, null, null, password, null));
//        }
//    }
	/*USAMOS UN MAPA DE MOMENTO*/

	    // Simulando la base de datos en memoria usando un HashMap
    private final Map<String, Usuario> userDatabase = new HashMap<>();

    // Verificar si el email está registrado en la "base de datos" en memoria
    public boolean isEmailRegistered(String email) {
        return userDatabase.containsKey(email);
    }

    // Validar las credenciales del usuario
    public boolean validateCredentials(String email, String password) {
        Usuario user = userDatabase.get(email);
        return user != null && user.getPassword().equals(password);
    }

    // Registrar un nuevo usuario (si no está ya registrado)
    public void register(String email, String password) {
        if (!isEmailRegistered(email)) {
            // Crear un nuevo usuario y almacenarlo en el Map
            userDatabase.put(email, new Usuario(email, password, null, null, null, null, null, password, null));
        }
    }

    // Método adicional para obtener un usuario si se necesita (opcional)
    public Usuario getUserByEmail(String email) {
        return userDatabase.get(email);
    }

}
