package com.strava.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strava.dao.SesionRepository;
import com.strava.dao.TipoAutentication;
import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.dao.UserRepository;
import com.strava.entity.Sesion;
import com.strava.entity.Usuario;
import com.strava.factory.AuthenticationFactory;
import com.strava.server.IMetaGateway;

@Service
public class SesionService {

    private final SesionRepository sesionRepository;
    private final UserRepository userRepository;
    private final AuthenticationFactory authenticationFactory;

    @Autowired
    public SesionService(SesionRepository sesionRepository, UserRepository userRepository, AuthenticationFactory authenticationFactory) {
        this.sesionRepository = sesionRepository;
        this.userRepository = userRepository;
        this.authenticationFactory = authenticationFactory;
    }

    /**
     * Crea una nueva sesión de entrenamiento.
     *
     * @param titulo       Título de la sesión.
     * @param deporte      Tipo de deporte ("cycling" o "running").
     * @param distancia    Distancia recorrida en kilómetros.
     * @param fechaInicio  Fecha de inicio de la sesión (formato: "YYYY-MM-DD").
     * @param horaInicio   Hora de inicio de la sesión (formato: "HH:mm:ss").
     * @param duracion     Duración de la sesión en minutos.
     * @param usuario      Usuario que realizó la sesión.
     * @return La sesión creada.
     */
    public Sesion crearSesion(String titulo, TipoDeporte deporte, TipoDistancia distancia, LocalDate fechaInicio, LocalTime horaInicio, double duracion, Usuario usuario) {
        if (!deporte.equals(TipoDeporte.CYCLING) && !deporte.equals(TipoDeporte.RUNNING)) {
            throw new IllegalArgumentException("Deporte no válido");
        }

        // Crear la nueva sesión
        Sesion nuevaSesion = new Sesion(0, titulo, deporte, distancia, fechaInicio, horaInicio, duracion, usuario);
        sesionRepository.save(nuevaSesion);  // Guarda la sesión en la base de datos
        return nuevaSesion;
    }

    /**
     * Crea una nueva sesión de entrenamiento utilizando un tipo de autenticación (Meta o Google).
     *
     * @param tipoGateway  Tipo de autenticación (Meta o Google).
     * @param titulo       Título de la sesión.
     * @param deporte      Tipo de deporte.
     * @param distancia    Distancia recorrida en kilómetros.
     * @param fechaInicio  Fecha de inicio de la sesión (formato: "YYYY-MM-DD").
     * @param horaInicio   Hora de inicio de la sesión (formato: "HH:mm:ss").
     * @param duracion     Duración de la sesión en minutos.
     * @param email        Correo electrónico del usuario.
     * @return La sesión creada.
     */
    public Sesion crearSesion(TipoAutentication tipoGateway, String titulo, TipoDeporte deporte, TipoDistancia distancia,
                              LocalDate fechaInicio, LocalTime horaInicio, double duracion, String email) {

        // Obtener el gateway adecuado (Meta o Google)
        IMetaGateway gateway = authenticationFactory.creacionGateways(tipoGateway);

        // Buscar al usuario en la base de datos por su email
        Optional<Usuario> usuarioOpt = userRepository.findByEmail(email);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado con el correo: " + email);
        }

        Usuario usuario = usuarioOpt.get();  // Obtener el usuario encontrado

        // Crear la nueva sesión
        Sesion nuevaSesion = new Sesion(0, titulo, deporte, distancia, fechaInicio, horaInicio, duracion, usuario);
        sesionRepository.save(nuevaSesion);  // Guarda la sesión en la base de datos
        return nuevaSesion;
    }

    /**
     * Obtiene las últimas 5 sesiones del usuario con filtros opcionales por fechas.
     *
     * @param fechaInicioDate Filtro opcional: fecha de inicio (formato: "YYYY-MM-DD").
     * @param fechaFinDate    Filtro opcional: fecha de fin (formato: "YYYY-MM-DD").
     * @return Lista de sesiones filtradas (máximo 5 sesiones).
     */
    public List<Sesion> obtenerSesiones(LocalDate fechaInicio, LocalDate fechaFin) {
        return sesionRepository.findAll().stream()
                .filter(sesion -> (fechaInicio == null || !sesion.getFechaInicio().isBefore(fechaInicio)) &&
                        (fechaFin == null || !sesion.getFechaInicio().isAfter(fechaFin)))
                .limit(5) // Máximo 5 sesiones
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una sesión específica por su título (simulando un ID único).
     *
     * @param titulo Título de la sesión.
     * @return La sesión encontrada.
     * @throws IllegalArgumentException Si no se encuentra la sesión.
     */
    public Sesion obtenerSesionPorTitulo(String titulo) {
        Optional<Sesion> sesionOpt = sesionRepository.findAll().stream()
                .filter(sesion -> sesion.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();

        if (sesionOpt.isPresent()) {
            return sesionOpt.get();
        } else {
            throw new IllegalArgumentException("No se encontró una sesión con el título: " + titulo);
        }
    }
}
