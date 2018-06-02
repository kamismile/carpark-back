package ru.neoflex.microservices.carpark.employees;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;

/**
 * @author mirzoevnik
 */
@EnableEurekaClient
@SpringBootApplication
@Import({MethodSecurityConfig.class,
		SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}
}
