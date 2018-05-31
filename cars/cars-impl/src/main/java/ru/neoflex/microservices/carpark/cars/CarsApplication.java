package ru.neoflex.microservices.carpark.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.config.FeignConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.neoflex.microservices.carpark.commons.config.SecurityConfig;

@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackageClasses = Car.class)
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
	SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class CarsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}
}
