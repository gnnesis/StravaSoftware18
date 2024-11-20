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

    
    public Reto crearReto(Reto reto, String token) {
  
        String email = TokenUtil.validarToken(token);
        reto.setEmail(email); 
        retos.add(reto);
        return reto;
    }

   
    public List<Reto> obtenerRetosActivos(String deporte, LocalDate fechaInicio, LocalDate fechaFin, String token) {
        String email = TokenUtil.validarToken(token);

        return retos.stream()
                .filter(reto -> reto.getEmail().equals(email))  
                .filter(reto -> (deporte == null || reto.getDeporte().equalsIgnoreCase(deporte)) &&
                        (fechaInicio == null || LocalDate.parse(reto.getFechainicio()).compareTo(fechaInicio) >= 0) &&
                        (fechaFin == null || LocalDate.parse(reto.getFechaFin()).compareTo(fechaFin) <= 0))
                .collect(Collectors.toList());
    }

   
    public String aceptarReto(String nombre, String token) {
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

    public List<Reto> obtenerRetosAceptados(String token) {
        String email = TokenUtil.validarToken(token);

        return retos.stream()
                .filter(reto -> reto.getEmail().equals(email)) 
                .collect(Collectors.toList());
    }
}
