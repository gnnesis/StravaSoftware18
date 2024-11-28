package com.strava.dto;

import java.sql.Date;

public class RetoDTO {

	private long id;
    private String nombre; 
    private Date fechaInicio;
    private Date fechaFin;
    private Float distancia;
    private Integer tiempoObjetivo;
    private String deporte;
    private String email;
    
    public RetoDTO() {
    	
    }
    
	public RetoDTO(long id, String nombre, Date fechaInicio, Date fechaFin, Float distancia, Integer tiempoObjetivo,
			String deporte,  String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.distancia = distancia;
		this.tiempoObjetivo = tiempoObjetivo;
		this.deporte = deporte;
		this.email = email;
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
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
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
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	
}

 