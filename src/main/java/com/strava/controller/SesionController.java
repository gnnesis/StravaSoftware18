package com.strava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SesionController {

    @Autowired
    private SesionService sesionService;

    @PostMapping
    public ResponseEntity<String> crearSesion(@RequestBody SesionDTO sesionDTO) {
        sesionService.crearSesion(sesionDTO);
        return ResponseEntity.ok("Sesión creada con éxito");
    }

    @GetMapping
    public ResponseEntity<List<SesionDTO>> listarSesiones() {
        List<SesionDTO> sesiones = sesionService.listarSesiones();
        return ResponseEntity.ok(sesiones);
    }
}