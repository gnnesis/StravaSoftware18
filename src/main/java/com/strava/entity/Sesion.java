package com.strava.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Sesion {
    private String id;
    private String titulo;
    private String deporte; //"cycling" o "running"
    private double distancia;
    private String fechaInicio; //"AAAA-MM-DD"
    private String horaInicio; //"HH:mm"
    private double duracion; 
 
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    public Sesion(String id, String titulo, String deporte, double distancia, String fechaInicio, String horaInicio, double duracion,
    		Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.deporte = deporte;
        this.distancia = distancia;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
        this.usuario = usuario;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Double getDistancia() {
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

    public String getHoraInicio() {
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
    

    public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	// Método toString para depuración
    @Override
    public String toString() {
        return "Sesion{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", deporte='" + deporte + '\'' +
                ", distancia=" + distancia +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", duracion=" + duracion +
                ", usuario='" + usuario + '\''+
                '}';
    }
}
