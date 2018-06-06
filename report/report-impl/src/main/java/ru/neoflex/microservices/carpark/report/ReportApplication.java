package ru.neoflex.microservices.carpark.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import ru.neoflex.microservices.carpark.commons.config.*;

/**
 * @author rmorenko
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"ru.neoflex.microservices.carpark.dicts.feign", "ru.neoflex.microservices.carpark.employees.feign"})
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
	SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class ReportApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);

    }

}
