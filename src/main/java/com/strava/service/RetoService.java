package com.strava.service;

import com.strava.dto.RetoDTO;
import com.strava.entity.Reto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RetoService {

    private List<Reto> retos = new ArrayList<>();

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
//    public Reto crearReto(long id, String nombre, String fechaInicio, String fechaFin, float distancia, Integer tiempoObjetivo, String deporte) {
//        Reto nuevoReto = new Reto(id, nombre, fechaInicio, fechaFin, distancia, tiempoObjetivo, deporte);
//        retos.add(nuevoReto);
//        return nuevoReto;
//    }
    
    
    public Reto crearReto(Reto reto) {
        retos.add(reto); 
        return reto; 
    }

    /**
     * Obtiene una lista de retos activos con filtros opcionales por deporte y rango de fechas.
     * Si no se aplican filtros, devuelve los últimos 5 retos creados.
     *
     * @param deporte      Filtro opcional por tipo de deporte.
     * @param fin 
     * @param inicio 
     * @return Lista de retos activos filtrados.
     */
    public List<Reto> obtenerRetosActivos(String deporte, LocalDate fechaInicio, LocalDate fechaFin) {
        return retos.stream()
                .filter(reto -> (deporte == null || reto.getDeporte().equalsIgnoreCase(deporte)) &&
                        (fechaInicio == null || LocalDate.parse(reto.getFechainicio()).compareTo(fechaInicio) >= 0) &&
                        (fechaFin == null || LocalDate.parse(reto.getFechaFin()).compareTo(fechaFin) <= 0))
                .collect(Collectors.toList());
    }


    /**
     * Marca un reto como aceptado.
     *
     * @param challengeId El ID del reto a aceptar.
     */
    public String aceptarReto(String nombre) {
        Optional<Reto> retoOpt = retos.stream()
                .filter(reto -> reto.getNombre().equalsIgnoreCase(nombre))
                .findFirst();

        if (retoOpt.isPresent()) {
            return "Reto aceptado: " + retoOpt.get().getNombre();
            
        } else {
            throw new IllegalArgumentException("El reto con nombre " + nombre + " no existe.");
        }
        
        
    }

   
	public Reto crearReto(long id, String nombre, String fechainicio, String fechaFin, double distancia,
			double tiempoObjetivo, String deporte) {
		// TODO Auto-generated method stub
		return null;	
	}
	

	public RetoDTO obtenerRetoActivo(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reto> obtenerRetosAceptados() {
        return retos;
    }

	
}
