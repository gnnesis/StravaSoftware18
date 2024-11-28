package com.strava.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    // Crear un nuevo reto
    @PostMapping ("/create")
    public ResponseEntity<Reto> crearReto(@RequestBody RetoDTO retoDTO, @RequestHeader("Authorization") String token) {
        Reto nuevoReto = new Reto(retoDTO.getId(), retoDTO.getNombre(), retoDTO.getFechaInicio(),
                retoDTO.getFechaFin(), retoDTO.getDistancia(), retoDTO.getTiempoObjetivo(), retoDTO.getDeporte(), retoDTO.getEmail());
        Reto retoCreado = retoService.crearReto(nuevoReto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(retoCreado);
    }
    
    // Obtener los retos activos (últimos 5 o filtrados por fecha o deporte)
    @GetMapping("/active")
    public ResponseEntity<List<Reto>> obtenerRetosActivos(
            @RequestParam(name = "deporte", required = false) String deporte,
            @RequestParam(name = "fechaInicio", required = false) String fechaInicio,
            @RequestParam(name = "fechaFin", required = false) String fechaFin,
            @RequestHeader("Authorization") String token) {
        LocalDate inicio = (fechaInicio != null) ? LocalDate.parse(fechaInicio) : null;
        LocalDate fin = (fechaFin != null) ? LocalDate.parse(fechaFin) : null;

        List<Reto> retos = retoService.obtenerRetosActivos(deporte, inicio, fin, token);
        return ResponseEntity.ok(retos);
    }

    // Aceptar un reto
    @PostMapping("/{challengeId}/accept")
    public String aceptarReto(@PathVariable("challengeId") String nombre, @RequestHeader("Authorization") String token) {
        return retoService.aceptarReto(nombre, token);
    }

    // Obtener los retos aceptados
    @GetMapping("/accepted")
    public ResponseEntity<List<Reto>> obtenerRetosAceptados(@RequestHeader("Authorization") String token) {
        List<Reto> retosAceptados = retoService.obtenerRetosAceptados(token);
        return ResponseEntity.ok(retosAceptados);
    }
}