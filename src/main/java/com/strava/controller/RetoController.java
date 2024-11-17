package com.strava.controller;

import com.strava.dto.RetoDTO;
import com.strava.entity.Reto;
import com.strava.service.RetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class RetoController {

    private final RetoService retoService;

    public RetoController(RetoService retoService) {
        this.retoService = retoService;
    }

    /**
     * Crear un nuevo reto.
     */
    @PostMapping
    public ResponseEntity<Reto> crearReto(@RequestBody RetoDTO retoDTO) {
           Reto nuevoReto = new Reto(retoDTO.getId(), retoDTO.getNombre(), retoDTO.getFechaInicio(), retoDTO.getFechaFin(), retoDTO.getDistancia(), retoDTO.getTiempoObjetivo(), retoDTO.getDeporte());
        return ResponseEntity.status(201).body(nuevoReto);
    }

    /**
     * Obtener los retos activos (últimos 5 o filtrados por fecha o deporte).
     */
    @GetMapping("/active")
    public ResponseEntity<List<Reto>> obtenerRetosActivos(
            @RequestParam(required = false) String deporte,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin
    ) {
        List<Reto> retos = retoService.obtenerRetosActivos(deporte, fechaInicio, fechaFin);
        return ResponseEntity.ok(retos);
    }

    /**
     * Aceptar un reto existente.
     */
    @PostMapping("/{challengeId}/accept")
    public ResponseEntity<Void> aceptarReto(@PathVariable String challengeId) {
        retoService.aceptarReto(challengeId);
        return ResponseEntity.noContent().build();
    }
}
