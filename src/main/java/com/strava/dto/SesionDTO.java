package com.strava.dto;

import java.time.LocalDateTime;

public class SesionDTO {

    private String titulo; // Título de la sesión
    private String deporte; // Tipo de deporte
    private double distancia; // Distancia recorrida (en kilómetros)
    private LocalDateTime fechaInicio; // Fecha y hora de inicio
    private double duracion; // Duración de la sesión (en minutos)

    // Constructor
    public SesionDTO() {}

    public SesionDTO(String titulo, String deporte, double distancia, LocalDateTime fechaInicio, double duracion) {
        this.titulo = titulo;
        this.deporte = deporte;
        this.distancia = distancia;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
