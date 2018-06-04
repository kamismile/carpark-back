package ru.neoflex.microservices.carpark.preorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import ru.neoflex.microservices.carpark.commons.config.*;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author mirzoevnik
 */
@EnableEurekaClient
@SpringBootApplication
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
        SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class})
public class PreordersApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(PreordersApplication.class, args);
    }
}
