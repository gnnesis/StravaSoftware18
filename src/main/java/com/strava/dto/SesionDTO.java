package com.strava.dto;

import java.time.LocalDateTime;

public class SesionDTO {

    private String titulo; // Título de la sesión
    private String deporte; // Tipo de deporte
    private double distancia; // Distancia recorrida (en kilómetros)
    private String fechaInicio; // Fecha y hora de inicio
    private String horaInicio;
    private double duracion; // Duración de la sesión (en minutos)

    // Constructor
    public SesionDTO() {}

    public SesionDTO(String titulo, String deporte, double distancia, String fechaInicio, String horaInicio,double duracion) {
        this.titulo = titulo;
        this.deporte = deporte;
        this.distancia = distancia;
        this.fechaInicio = fechaInicio;
        this.horaInicio= horaInicio;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

	public String gethoraInicio() {
        return horaInicio;
    }
	
	public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
	
	
    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
