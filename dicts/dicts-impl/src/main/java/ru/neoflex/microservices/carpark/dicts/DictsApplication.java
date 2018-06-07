package ru.neoflex.microservices.carpark.dicts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import ru.neoflex.microservices.carpark.commons.config.FeignConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.neoflex.microservices.carpark.commons.config.SecurityConfig;

@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
	SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class DictsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DictsApplication.class, args);
	}
}