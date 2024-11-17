package com.strava.entity;

public class Usuario {
    private String email;
    private String nombre;
    private String fechaNacimiento; // Formato: "YYYY-MM-DD"
    private Double peso; // Peso en kg (opcional)
    private Integer altura; // Altura en cm (opcional)
    private Integer frecuenciaMaxima; // Máxima frecuencia cardíaca en bpm (opcional)
    private Integer frecuenciaReposo; // Frecuencia cardíaca en reposo en bpm (opcional)
    private String password;

    // Constructor completo
    public Usuario(String email, String nombre, String fechaNacimiento, Double peso, Integer altura, Integer frecuenciaMaxima, Integer frecuenciaReposo, String password) {
        this.email = email;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.frecuenciaMaxima = frecuenciaMaxima;
        this.frecuenciaReposo = frecuenciaReposo;
        this.password = password;
    }

    // Constructor básico (para usos opcionales)
    public Usuario(String email, String nombre, String fechaNacimiento, String password) {
        this(email, nombre, fechaNacimiento, null, null, null, null, password);
    }

    // Getters y Setters
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
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

    // Método toString para facilitar la impresión de datos
    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", frecuenciaMaxima=" + frecuenciaMaxima +
                ", frecuenciaReposo=" + frecuenciaReposo +
                ", password=" + password +
                '}';
    }
}
