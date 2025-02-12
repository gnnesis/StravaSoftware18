package com.strava.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.strava")
@EntityScan("com.strava.entity")
@EnableJpaRepositories("com.strava.dao")
public class ServerCentralApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerCentralApplication.class, args);
    }
}
