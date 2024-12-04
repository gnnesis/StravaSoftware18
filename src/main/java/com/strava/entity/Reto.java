package com.strava.entity;

import java.sql.Date;

import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;

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
    private TipoDistancia distancia;  //tiempo u distandia enumeracion de tipo HECHO
    private Integer tiempoObjetivo;
    private  TipoDeporte deporte;  // "cycling" o "running"  enumeracion HECHO
    private String email;  // Email del usuario que creó el reto para el token
    //lista de ususario apuntados al reto
    
    /**
     * cada reto tiene un usuario creador
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


	public Reto(Long id, String nombre, Date fechaInicio, Date fechaFin, TipoDistancia distancia,
			Integer tiempoObjetivo, TipoDeporte deporte, String email, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.distancia = distancia;
		this.tiempoObjetivo = tiempoObjetivo;
		this.deporte = deporte;
		this.email = email;
		this.usuario = usuario;
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


	public TipoDistancia getDistancia() {
		return distancia;
	}


	public void setDistancia(TipoDistancia distancia) {
		this.distancia = distancia;
	}


	public Integer getTiempoObjetivo() {
		return tiempoObjetivo;
	}


	public void setTiempoObjetivo(Integer tiempoObjetivo) {
		this.tiempoObjetivo = tiempoObjetivo;
	}


	public TipoDeporte getDeporte() {
		return deporte;
	}


	public void setDeporte(TipoDeporte deporte) {
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