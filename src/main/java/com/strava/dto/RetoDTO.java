package com.strava.dto;

import java.time.LocalDate;

public class RetoDTO {

	private long id;
    private String nombre; // Nombre del reto
    private String fechaInicio; // Fecha de inicio del reto
    private String fechaFin; // Fecha de fin del reto
    private Float distancia; // Distancia del reto (en kilómetros)
    private Integer tiempoObjetivo; // Tiempo objetivo en minutos
    private String deporte;
    
    public RetoDTO() {
    	
    }
    
	public RetoDTO(long id, String nombre, String fechaInicio, String fechaFin, Float distancia, Integer tiempoObjetivo,
			String deporte) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.distancia = distancia;
		this.tiempoObjetivo = tiempoObjetivo;
		this.deporte = deporte;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Float getDistancia() {
		return distancia;
	}
	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}
	public Integer getTiempoObjetivo() {
		return tiempoObjetivo;
	}
	public void setTiempoObjetivo(Integer tiempoObjetivo) {
		this.tiempoObjetivo = tiempoObjetivo;
	}
	public String getDeporte() {
		return deporte;
	}
	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}
    
	
}// Deporte asociado al reto

 