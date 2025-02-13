package com.strava.dto;

import java.sql.Date;

import com.strava.dao.TipoAutentication;

public class UsuarioDTO {

    private String email;
    private String nombre;
    private Date fechaNacimiento;
    private double peso;
    private double altura;
    private int frecuenciaCardiacaMaxima;
    private int frecuenciaCardiacaReposo;
    private String password;
    private TipoAutentication tipoAutentication;

	public UsuarioDTO() {
		super();
	}
	
 
    public UsuarioDTO(String email, String nombre, Date fechaNacimiento, double peso, int altura, 
                      int frecuenciaCardiacaMaxima, int frecuenciaCardiacaReposo, String password,
                      TipoAutentication tipoAutentication) {
        this.email = email;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.frecuenciaCardiacaMaxima = frecuenciaCardiacaMaxima;
        this.frecuenciaCardiacaReposo = frecuenciaCardiacaReposo;
        this.password = password;
        this.tipoAutentication = tipoAutentication;
    }


    // Getters y setters
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getFrecuenciaCardiacaMaxima() {
        return frecuenciaCardiacaMaxima;
    }

    public void setFrecuenciaCardiacaMaxima(int frecuenciaCardiacaMaxima) {
        this.frecuenciaCardiacaMaxima = frecuenciaCardiacaMaxima;
    }

    public int getFrecuenciaCardiacaReposo() {
        return frecuenciaCardiacaReposo;
    }

    public void setFrecuenciaCardiacaReposo(int frecuenciaCardiacaReposo) {
        this.frecuenciaCardiacaReposo = frecuenciaCardiacaReposo;
    }
    
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public TipoAutentication getTipoAutentication() {
		return tipoAutentication;
	}
	
	public void setTipoAutentication(TipoAutentication tipoAutentication) {
		this.tipoAutentication = tipoAutentication;
	}
	
}
