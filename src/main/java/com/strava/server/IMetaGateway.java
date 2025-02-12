package com.strava.server;

import java.time.LocalDate;
import java.time.LocalTime;

import com.strava.dao.TipoAutentication;
import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.entity.Usuario;

public interface IMetaGateway {
	String creacionSesion(long id, String titulo, TipoDeporte deporte,  TipoDistancia distancia, LocalDate fechaInicio, LocalTime horaInicio, double duracion, Usuario user);
	
	// introducir los demás metodos mas adelante
	
}
