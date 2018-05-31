package ru.neoflex.microservices.carpark.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.SecurityConfig;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@Import({MethodSecurityConfig.class, SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class AccessApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccessApplication.class, args);
	}
}
