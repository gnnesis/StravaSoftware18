package com.strava.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.strava")
public class ServerCentralApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerCentralApplication.class, args);
    }
}
