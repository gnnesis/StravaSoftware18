package com.strava.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strava.dto.SesionDTO;
import com.strava.entity.Sesion;
import com.strava.entity.Usuario;
import com.strava.service.SesionService;
import com.strava.service.UsuarioService;
import com.strava.utils.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sessions")
public class SesionController {

    private final SesionService sesionService;
    private final UsuarioService usuarioService;

    public SesionController(SesionService sesionService, UsuarioService usuarioService) {
        this.sesionService = sesionService;
        this.usuarioService = usuarioService;
    }

    /**
     * Crear una nueva sesión de entrenamiento.
     * La sesión se asocia al usuario cuyo email está en el token de la solicitud.
     */
    @PostMapping("/crearSesion")
    public ResponseEntity<Sesion> crearSesion(@RequestBody SesionDTO sesionDTO, HttpServletRequest request) {
        // Obtener el token del encabezado Authorization
        String token = request.getHeader("Authorization");

        if (token == null) {
            return ResponseEntity.status(401).body(null); // Token no proporcionado
        }

        try {
            // Validar el token y obtener el email del usuario
            String email = TokenUtil.validarToken(token); // Esto devolverá el email del usuario

            // Buscar al usuario por su email
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);
            if (usuario == null) {
                return ResponseEntity.status(404).body(null); // Usuario no encontrado
            }

            // Verificar que el deporte sea válido
            if (!sesionDTO.getDeporte().equals("cycling") && !sesionDTO.getDeporte().equals("running")) {
                return ResponseEntity.status(400).body(null); // Deporte inválido
            }

            // Crear la sesión y asociarla al usuario
            Sesion nuevaSesion = sesionService.crearSesion(
                    sesionDTO.getTitulo(),
                    sesionDTO.getDeporte(),
                    sesionDTO.getDistancia(),
                    sesionDTO.getFechaInicio(),
                    sesionDTO.gethoraInicio(),
                    sesionDTO.getDuracion(),
                    usuario // Asociamos la sesión al usuario
            );

            return ResponseEntity.status(201).body(nuevaSesion); // Retorna la sesión creada

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null); // Token inválido o expirado
        }
    }

    // Otros métodos como obtener sesiones, etc.
    
    @GetMapping("/obtenerSesiones")
    public ResponseEntity<List<Sesion>> obtenerSesiones(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            HttpServletRequest request) {

        // Obtener el token de la solicitud
        String token = request.getHeader("Authorization");

        if (token == null) {
            return ResponseEntity.status(401).body(null); // Si no hay token, devolver error 401
        }

        try {
            // Validar el token y obtener el email del usuario
            String email = TokenUtil.validarToken(token);
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email); // Obtener usuario por email

            // Filtrar las sesiones según las fechas (si se proporcionan)
            List<Sesion> sesiones = sesionService.obtenerSesiones(fechaInicio, fechaFin);
            return ResponseEntity.ok(sesiones);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null); // Si el token es inválido
        }
    }
}
