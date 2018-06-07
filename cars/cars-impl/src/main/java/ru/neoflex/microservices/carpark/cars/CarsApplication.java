package ru.neoflex.microservices.carpark.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.config.FeignConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.neoflex.microservices.carpark.commons.config.SecurityConfig;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackageClasses = Car.class)
@EnableHystrix
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
	SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class CarsApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}
}
