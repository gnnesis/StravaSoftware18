package com.strava.entity;

import java.util.Date;

public class Reto {

    private Long id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private Float distancia;
    private Integer tiempoObjetivo;
    private String deporte;  // "cycling" o "running"


	public Reto(Long id, String nombre, String fechainicio, String fechaFin, Float distancia, Integer tiempoObjetivo, String deporte) {
            this.id = id;
            this.nombre = nombre;
            this.fechaInicio = fechainicio;
            this.fechaFin = fechaFin;
            this.distancia = distancia;
            this.tiempoObjetivo = tiempoObjetivo;
            this.deporte = deporte;
        }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getFechainicio() {
		return fechaInicio;
	}


	public void setFechainicio(String fechainicio) {
		this.fechaInicio = fechainicio;
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


	@Override
	public String toString() {
		return "Reto [id=" + id + ", nombre=" + nombre + ", fechainicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", distancia=" + distancia + ", tiempoObjetivo=" + tiempoObjetivo + ", deporte=" + deporte + "]";
	}
	
}