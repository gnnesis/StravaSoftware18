package com.strava.controller;

import com.strava.dto.SesionDTO;
import com.strava.entity.Sesion;
import com.strava.service.SesionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    /**
     * Crear una nueva sesión de entrenamiento.
     */
    @PostMapping
    public ResponseEntity<Sesion> crearSesion(@RequestBody SesionDTO sesionDTO) {
        Sesion nuevaSesion = sesionService.crearSesion(
                sesionDTO.getTitulo(),
                sesionDTO.getDeporte(),
                sesionDTO.getDistancia(),
                sesionDTO.getFechaInicio(),
                sesionDTO.getHoraInicio(),
                sesionDTO.getDuracion()
        );
        return ResponseEntity.status(201).body(nuevaSesion);
    }

    /**
     * Obtener las últimas 5 sesiones de entrenamiento o filtrar por fecha.
     */
    @GetMapping
    public ResponseEntity<List<Sesion>> obtenerSesiones(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin
    ) {
        List<Sesion> sesiones = sesionService.obtenerSesiones(fechaInicio, fechaFin);
        return ResponseEntity.ok(sesiones);
    }
}
