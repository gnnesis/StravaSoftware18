package com.strava.factory;

import com.strava.dao.TipoAutentication;
import com.strava.server.IMetaGateway;
import com.strava.server.MetaGateway;

public class AuthenticationFactory {

	public static IMetaGateway creacionGateways(TipoAutentication t) {
		switch (t) {
		case GOOGLE: 
			return null;
		case META:
			return new MetaGateway();
		}
		return null;
	}
}
