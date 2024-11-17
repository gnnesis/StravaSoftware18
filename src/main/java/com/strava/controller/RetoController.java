package com.strava.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strava.dto.RetoDTO;
import com.strava.entity.Reto;
import com.strava.service.RetoService;

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
        // El objeto RetoDTO se pasa como parámetro
        Reto nuevoReto = new Reto(retoDTO.getId(), retoDTO.getNombre(), retoDTO.getFechaInicio(),
                retoDTO.getFechaFin(), retoDTO.getDistancia(), retoDTO.getTiempoObjetivo(), retoDTO.getDeporte());
        Reto retoCreado = retoService.crearReto(nuevoReto);
        return ResponseEntity.status(HttpStatus.CREATED).body(retoCreado);
    }


    /**
     * Obtener los retos activos (últimos 5 o filtrados por fecha o deporte).
     */
    @GetMapping("/active")
    public ResponseEntity<List<Reto>> obtenerRetosActivos(
            @RequestParam(name = "deporte", required = false) String deporte,
            @RequestParam(name = "fechaInicio", required = false) String fechaInicio,
            @RequestParam(name = "fechaFin", required = false) String fechaFin
    ) {
        // Convertir las fechas de inicio y fin a LocalDate
        LocalDate inicio = (fechaInicio != null) ? LocalDate.parse(fechaInicio) : null;
        LocalDate fin = (fechaFin != null) ? LocalDate.parse(fechaFin) : null;
        
        // Llamar al servicio para obtener los retos activos
        List<Reto> retos = retoService.obtenerRetosActivos(deporte, inicio, fin);
        
        return ResponseEntity.ok(retos);
    }

    /**
     * Aceptar un reto existente.
     */
    @PostMapping("/{challengeId}/accept")
    public String aceptarReto(@PathVariable("challengeId") String nombre) {
        // Buscar el reto por nombre
        for (Reto reto : retoService.obtenerRetosActivos(null, null, null)) {
            if (reto.getNombre().equalsIgnoreCase(nombre)) {
                return "Reto aceptado: " + reto.getNombre();
            }
        }
        throw new IllegalArgumentException("El reto con nombre " + nombre + " no existe.");
    }




}
