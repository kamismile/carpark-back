package ru.neoflex.microservices.carpark.preorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author mirzoevnik
 */
@EnableEurekaClient
@SpringBootApplication
public class PreordersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreordersApplication.class, args);
    }
}
