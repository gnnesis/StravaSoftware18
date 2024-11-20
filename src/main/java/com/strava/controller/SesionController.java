package com.strava.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        
        String token = request.getHeader("Authorization");

        if (token == null) {
            return ResponseEntity.status(401).body(null); 
        }

        try {
            
            String email = TokenUtil.validarToken(token);

            
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);
            if (usuario == null) {
                return ResponseEntity.status(404).body(null);
            }

            //Se verifica que el deporte sea cycling o running
            if (!sesionDTO.getDeporte().equals("cycling") && !sesionDTO.getDeporte().equals("running")) {
                return ResponseEntity.status(400).body(null); 
            }

            
            Sesion nuevaSesion = sesionService.crearSesion(
                    sesionDTO.getTitulo(),
                    sesionDTO.getDeporte(),
                    sesionDTO.getDistancia(),
                    sesionDTO.getFechaInicio(),
                    sesionDTO.gethoraInicio(),
                    sesionDTO.getDuracion(),
                    usuario 
            );

            return ResponseEntity.status(201).body(nuevaSesion); 

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    
    @GetMapping("/obtenerSesiones")
    public ResponseEntity<Map<String, Object>> obtenerSesiones(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            HttpServletRequest request) {

        
        String token = request.getHeader("Authorization");

        if (token == null) {
            return ResponseEntity.status(401).body(null); 
        }
        try {
            
            String email = TokenUtil.validarToken(token);
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email); 

            
            List<Sesion> sesiones = sesionService.obtenerSesiones(fechaInicio, fechaFin);

            
            Map<String, Object> response = new HashMap<>();
            response.put("email", usuario.getEmail());
            response.put("sesiones", sesiones);

            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null); 
        }
      }
}