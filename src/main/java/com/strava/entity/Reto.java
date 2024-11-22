package com.strava.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Reto {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Float distancia;
    private Integer tiempoObjetivo;
    private String deporte;  // "cycling" o "running"
    private String email;  // Email del usuario que creó el reto para el token
    
    
    /**
     * cada reto tiene un usuario creador
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
	public Reto(Long id, String nombre, Date fechainicio, Date fechaFin, Float distancia, Integer tiempoObjetivo, String deporte, String email) {
            this.id = id;
            this.nombre = nombre;
            this.fechaInicio = fechainicio;
            this.fechaFin = fechaFin;
            this.distancia = distancia;
            this.tiempoObjetivo = tiempoObjetivo;
            this.deporte = deporte;
            this.email = email;
            
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


	public Date getFechainicio() {
		return fechaInicio;
	}


	public void setFechainicio(Date fechainicio) {
		this.fechaInicio = fechainicio;
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


	@Override
	public String toString() {
		return "Reto [id=" + id + ", nombre=" + nombre + ", fechainicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", distancia=" + distancia + ", tiempoObjetivo=" + tiempoObjetivo + ", deporte=" + deporte + ", email="+ email +"]";
	}
	
}