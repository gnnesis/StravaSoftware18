package com.strava.external;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@SpringBootApplication
//public class ServerCentralApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(ServerCentralApplication.class, args);
//    }
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.strava.server.MetaGateway;

@SpringBootApplication
public class ServerCentralApplication {

    public static void main(String[] args) {
        // Inicia Spring Boot y obtiene el contexto de la aplicación
        ApplicationContext context = SpringApplication.run(ServerCentralApplication.class, args);

        // Obtiene el bean de MetaGateway gestionado por Spring
        MetaGateway client = context.getBean(MetaGateway.class);

        // Prueba de creación de sesión
        String sessionId = client.crearSesionEntrenamiento(
            "test@example.com",
            "TIEMPO",
            10.5,
            "RUNNING"
        );

        System.out.println("Sesión creada con ID: " + sessionId);
    }
}
