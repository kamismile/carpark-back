package ru.neoflex.microservices.carpark.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;
import ru.neoflex.microservices.carpark.commons.config.SecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.FeignConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;

/**
 * Intial class.
 *
 * @author mirzoevnik
 */
@EnableEurekaClient
@SpringBootApplication
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
        SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }
}
