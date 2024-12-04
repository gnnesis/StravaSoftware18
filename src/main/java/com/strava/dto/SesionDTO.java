package com.strava.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;

public class SesionDTO {

    private String titulo;
    private TipoDeporte deporte;
    private TipoDistancia distancia;
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private double duracion;

  
    public SesionDTO() {}

	public SesionDTO(String titulo, TipoDeporte deporte, TipoDistancia distancia, LocalDate fechaInicio,
			LocalTime horaInicio, double duracion) {
		this.titulo = titulo;
		this.deporte = deporte;
		this.distancia = distancia;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.duracion = duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoDeporte getDeporte() {
		return deporte;
	}

	public void setDeporte(TipoDeporte deporte) {
		this.deporte = deporte;
	}

	public TipoDistancia getDistancia() {
		return distancia;
	}

	public void setDistancia(TipoDistancia distancia) {
		this.distancia = distancia;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	
	
}
