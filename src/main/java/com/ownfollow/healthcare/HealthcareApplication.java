package com.ownfollow.healthcare;

import com.ownfollow.healthcare.configuration.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class HealthcareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthcareApplication.class, args);
    }
}
