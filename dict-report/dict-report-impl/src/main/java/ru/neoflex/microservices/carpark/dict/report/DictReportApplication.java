package ru.neoflex.microservices.carpark.dict.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.neoflex.microservices.carpark.commons.config.*;
import java.util.concurrent.Executor;

/**
 * @author vanosov
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"ru.neoflex.microservices.carpark.dicts.feign"})
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
		SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
@EnableAsync
public class DictReportApplication {


	public static void main(String[] args) {
		SpringApplication.run(DictReportApplication.class, args);

    }

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(16);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("feign_");
		executor.initialize();
		return executor;
	}

}
