package ru.neoflex.microservices.carpark.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.SecurityConfig;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.reciver.CarsMessageReceiver;
import ru.neoflex.microservices.carpark.report.reciver.Sender;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
//@Import({MethodSecurityConfig.class, SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class ReportApplication {

	@Autowired
	private Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);

    }

}
