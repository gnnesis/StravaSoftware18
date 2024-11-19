package com.strava.service;

import com.strava.dto.RetoDTO;
import com.strava.entity.Reto;
import com.strava.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RetoService {

    private List<Reto> retos = new ArrayList<>();

    // Método para crear un reto asociado al usuario
    public Reto crearReto(Reto reto, String token) {
        // Validar el token y obtener el email del usuario
        String email = TokenUtil.validarToken(token);

        // Asociamos el email al reto (asumiendo que Reto tiene un campo 'email')
        reto.setEmail(email);  // Debes añadir un campo 'email' en la clase Reto

        retos.add(reto);
        return reto;
    }

    // Método para obtener los retos activos del usuario basado en el token
    public List<Reto> obtenerRetosActivos(String deporte, LocalDate fechaInicio, LocalDate fechaFin, String token) {
        // Validar el token y obtener el email del usuario
        String email = TokenUtil.validarToken(token);

        return retos.stream()
                .filter(reto -> reto.getEmail().equals(email))  // Filtrar por email del usuario
                .filter(reto -> (deporte == null || reto.getDeporte().equalsIgnoreCase(deporte)) &&
                        (fechaInicio == null || LocalDate.parse(reto.getFechainicio()).compareTo(fechaInicio) >= 0) &&
                        (fechaFin == null || LocalDate.parse(reto.getFechaFin()).compareTo(fechaFin) <= 0))
                .collect(Collectors.toList());
    }

    // Método para aceptar un reto (lo mismo, filtrando por email)
    public String aceptarReto(String nombre, String token) {
        // Validar el token y obtener el email del usuario
        String email = TokenUtil.validarToken(token);

        Optional<Reto> retoOpt = retos.stream()
                .filter(reto -> reto.getNombre().equalsIgnoreCase(nombre) && reto.getEmail().equals(email))  // Filtrar por nombre y email
                .findFirst();

        if (retoOpt.isPresent()) {
            return "Reto aceptado: " + retoOpt.get().getNombre();
        } else {
            throw new IllegalArgumentException("El reto con nombre " + nombre + " no existe o no es de tu propiedad.");
        }
    }

    // Método para obtener todos los retos aceptados del usuario
    public List<Reto> obtenerRetosAceptados(String token) {
        // Validar el token y obtener el email del usuario
        String email = TokenUtil.validarToken(token);

        return retos.stream()
                .filter(reto -> reto.getEmail().equals(email))  // Filtrar por email del usuario
                .collect(Collectors.toList());
    }
}
