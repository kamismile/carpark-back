package ru.vtb.microservices.carpark.dict.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import ru.vtb.microservices.carpark.commons.config.*;

/**
 * @author vanosov
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"ru.vtb.microservices.carpark.dicts.feign"})
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
        SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class})
public class DictReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictReportApplication.class, args);

    }
}