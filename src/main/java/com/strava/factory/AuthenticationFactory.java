package com.strava.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.strava.dao.SesionRepository;
import com.strava.dao.TipoAutentication;
import com.strava.dao.UserRepository;
import com.strava.server.IMetaGateway;
import com.strava.server.MetaGateway;

@Component
public class AuthenticationFactory {
    
    private final SesionRepository sesionRepository;
    private final UserRepository userRepository;
    
    @Autowired
    public AuthenticationFactory(SesionRepository sesionRepository, UserRepository userRepository) {
        this.sesionRepository = sesionRepository;
        this.userRepository = userRepository;
    }
    
    public static IMetaGateway creacionGateways(TipoAutentication t) {
        switch (t) {
            case GOOGLE: 
                return null; // Implementar cuando sea necesario
            case META:
                return new MetaGateway();
            default:
                return null;
        }
    }

}
