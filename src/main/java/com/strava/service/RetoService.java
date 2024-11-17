package com.strava.service;

import com.strava.entity.Reto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RetoService {

    // Simulación de almacenamiento en memoria
    private final List<Reto> retos = new ArrayList<>();

    /**
     * Crea un nuevo reto y lo guarda en la lista de retos.
     *
     * @param nombre       Nombre del reto.
     * @param fechaInicio  Fecha de inicio del reto (formato: "YYYY-MM-DD").
     * @param fechaFin     Fecha de fin del reto (formato: "YYYY-MM-DD").
     * @param metaDistancia Meta en distancia (en kilómetros, opcional).
     * @param metaDuracion  Meta en duración (en minutos, opcional).
     * @param deporte       Tipo de deporte ("cycling" o "running").
     * @return El reto creado.
     */
    public Reto crearReto(String nombre, String fechaInicio, String fechaFin, Double metaDistancia, Integer metaDuracion, String deporte) {
        Reto nuevoReto = new Reto(nombre, fechaInicio, fechaFin, metaDistancia, metaDuracion, deporte);
        retos.add(nuevoReto);
        return nuevoReto;
    }

    /**
     * Obtiene una lista de retos activos con filtros opcionales por deporte y rango de fechas.
     * Si no se aplican filtros, devuelve los últimos 5 retos creados.
     *
     * @param deporte      Filtro opcional por tipo de deporte.
     * @param fechaInicio  Filtro opcional por fecha de inicio.
     * @param fechaFin     Filtro opcional por fecha de fin.
     * @return Lista de retos activos filtrados.
     */
    public List<Reto> obtenerRetosActivos(String deporte, String fechaInicio, String fechaFin) {
        return retos.stream()
                .filter(reto -> (deporte == null || reto.getDeporte().equalsIgnoreCase(deporte)) &&
                        (fechaInicio == null || reto.getFechaInicio().compareTo(fechaInicio) >= 0) &&
                        (fechaFin == null || reto.getFechaFin().compareTo(fechaFin) <= 0))
                .limit(5) // Devolver un máximo de 5 retos
                .collect(Collectors.toList());
    }

    /**
     * Marca un reto como aceptado.
     *
     * @param challengeId El ID del reto a aceptar.
     */
    public void aceptarReto(String challengeId) {
        Optional<Reto> retoOpt = retos.stream()
                .filter(reto -> reto.getNombre().equalsIgnoreCase(challengeId))
                .findFirst();

        if (retoOpt.isPresent()) {
            // Simulación: No hacemos cambios en la entidad, solo validamos el reto encontrado.
            System.out.println("Reto aceptado: " + retoOpt.get().getNombre());
        } else {
            throw new IllegalArgumentException("El reto con ID " + challengeId + " no existe.");
        }
    }
}
