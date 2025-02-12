package com.strava.entity;

import java.sql.Date;

import com.strava.dao.TipoAutentication;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Usuario {
	@Id
    private String email;
    private String nombre;
    private Date fechaNacimiento; 
    private Double peso; 
    private Double altura; 
    private Integer frecuenciaMaxima;
    private Integer frecuenciaReposo;
    private String password;
    private TipoAutentication tipoAutentication ;

    public Usuario(String email, String nombre, Date fechaNacimiento, Double peso, Double altura, Integer frecuenciaMaxima, Integer frecuenciaReposo, String password, TipoAutentication tipoAutentication2) {
        this.email = email;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.frecuenciaMaxima = frecuenciaMaxima;
        this.frecuenciaReposo = frecuenciaReposo;
        this.password = password;
    }

    public Usuario(String email, String nombre, Date fechaNacimiento, String password, TipoAutentication tipoAutentication) {
        this(email, nombre, fechaNacimiento, null, null, null, null, password, tipoAutentication);
    }


    public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Integer getFrecuenciaMaxima() {
        return frecuenciaMaxima;
    }

    public void setFrecuenciaMaxima(Integer frecuenciaMaxima) {
        this.frecuenciaMaxima = frecuenciaMaxima;
    }

    public Integer getFrecuenciaReposo() {
        return frecuenciaReposo;
    }

    public void setFrecuenciaReposo(Integer frecuenciaReposo) {
        this.frecuenciaReposo = frecuenciaReposo;
    }
    
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public TipoAutentication getTipoAutenticacion() {
        return tipoAutentication;
    }

    public void setTipoAutenticacion(TipoAutentication tipoAutenticacion) {
        this.tipoAutentication = tipoAutenticacion;
    }

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", peso="
				+ peso + ", altura=" + altura + ", frecuenciaMaxima=" + frecuenciaMaxima + ", frecuenciaReposo="
				+ frecuenciaReposo + ", password=" + password + ", tipoAutentication=" + tipoAutentication + "]";
	}

    
}
