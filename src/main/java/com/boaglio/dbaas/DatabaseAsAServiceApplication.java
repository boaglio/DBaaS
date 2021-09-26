package com.boaglio.dbaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Spring Boot Class
 * 
 * @author Fernando Boaglio
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.boaglio")
public class DatabaseAsAServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseAsAServiceApplication.class, args);
    }

}
