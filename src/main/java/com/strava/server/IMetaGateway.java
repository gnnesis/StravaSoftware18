package com.strava.server;

import java.time.LocalTime;

import com.strava.dao.TipoAutentication;
import com.strava.dao.TipoDeporte;
import com.strava.dao.TipoDistancia;
import com.strava.dto.UsuarioDTO;
import com.strava.entity.Usuario;

public interface IMetaGateway {
	String login(UsuarioDTO usuario);
	
	// introducir los demás metodos mas adelante
	
}
