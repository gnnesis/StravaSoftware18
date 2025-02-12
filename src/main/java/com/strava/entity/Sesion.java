package com.strava.entity;


import java.time.LocalDate;
import java.time.LocalTime;

import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sesiones")
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String titulo;
    
    @Enumerated(EnumType.STRING)
    private TipoDeporte deporte;
    
    @Enumerated(EnumType.STRING)
    private TipoDistancia distancia;
    
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;
    
    private double duracion;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Sesion(long id, String titulo, TipoDeporte deporte, TipoDistancia distancia, LocalDate fechaInicio,
			LocalTime horaInicio, double duracion, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.deporte = deporte;
		this.distancia = distancia;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.duracion = duracion;
		this.usuario = usuario;
	}

	public Sesion() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
