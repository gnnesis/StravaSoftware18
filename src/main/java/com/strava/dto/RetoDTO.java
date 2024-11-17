package com.strava.dto;

import java.time.LocalDate;

public class RetoDTO {

    private String nombre; // Nombre del reto
    private LocalDate fechaInicio; // Fecha de inicio del reto
    private LocalDate fechaFin; // Fecha de fin del reto
    private double distancia; // Distancia del reto (en kilómetros)
    private double tiempoObjetivo; // Tiempo objetivo en minutos
    private String deporte; // Deporte asociado al reto

    // Constructor
    public RetoDTO() {}

    public RetoDTO(String nombre, LocalDate fechaInicio, LocalDate fechaFin, double distancia, double tiempoObjetivo, String deporte) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.distancia = distancia;
        this.tiempoObjetivo = tiempoObjetivo;
        this.deporte = deporte;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTiempoObjetivo() {
        return tiempoObjetivo;
    }

    public void setTiempoObjetivo(double tiempoObjetivo) {
        this.tiempoObjetivo = tiempoObjetivo;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }
}
