package com.strava.dto;


// Clase que representa una respuesta de la API

public class RespuestaDTO {

    private String codigo;  // Ejemplo: "200 OK"
    private String mensaje; // Mensaje descriptivo del resultado de la operación

    // Constructor
    public RespuestaDTO(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

