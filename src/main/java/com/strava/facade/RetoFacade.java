package com.strava.facade;


import org.springframework.stereotype.Component;

import com.strava.service.RetoService;
import com.strava.dto.RetoDTO;
import com.strava.entity.Reto;

@Component
public class RetoFacade {

    private final RetoService retoService;

    public RetoFacade(RetoService retoService) {
        this.retoService = retoService;
    }


    public Reto crearReto(Reto retoDTO) {
        return retoService.crearReto(retoDTO); 
    }


    public RetoDTO obtenerRetoActivo(String nombre) {
        return retoService.obtenerRetoActivo(nombre);
    }


    public String aceptarReto(String nombre) {
        return retoService.aceptarReto(nombre);
    }
}
