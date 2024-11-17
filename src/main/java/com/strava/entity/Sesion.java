package com.strava.entity;

public class Sesion {
    private String id; // Identificador único de la sesión
    private String titulo; // Título de la sesión
    private String deporte; // Tipo de deporte: "cycling" o "running"
    private double distancia; // Distancia en kilómetros
    private String fechaInicio; // Fecha de inicio en formato "YYYY-MM-DD"
    private String horaInicio; // Hora de inicio en formato "HH:mm"
    private double duracion; // Duración en minutos

    // Constructor completo
    public Sesion(String id, String titulo, String deporte, double distancia, String fechaInicio, String horaInicio, double duracion) {
        this.id = id;
        this.titulo = titulo;
        this.deporte = deporte;
        this.distancia = distancia;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
    }

    // Getters y Setters
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

    public void setDistancia(Double distancia) {
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
                '}';
    }
}
