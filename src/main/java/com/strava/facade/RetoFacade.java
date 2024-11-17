package com.strava.facade;


import org.springframework.stereotype.Component;

import com.ejemplo.service.RetoService;
import com.strava.dto.RetoDTO;

@Component
public class RetoFacade {

    private final RetoService retoService;

    public RetoFacade(RetoService retoService) {
        this.retoService = retoService;
    }

    // Crear un nuevo reto
    public RetoDTO crearReto(RetoDTO retoDTO) {
        return retoService.crearReto(retoDTO); // Llama al servicio para crear el reto
    }

    // Obtener información sobre un reto activo
    public RetoDTO obtenerRetoActivo(String nombre) {
        return retoService.obtenerRetoActivo(nombre); // Llama al servicio para obtener el reto activo
    }

    // Aceptar un reto
    public String aceptarReto(String nombre) {
        return retoService.aceptarReto(nombre); // Llama al servicio para aceptar un reto
    }
}
