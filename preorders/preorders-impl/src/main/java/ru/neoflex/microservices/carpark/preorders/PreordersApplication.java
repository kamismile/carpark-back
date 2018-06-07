/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.preorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import ru.neoflex.microservices.carpark.commons.config.FeignConfig;
import ru.neoflex.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.neoflex.microservices.carpark.commons.config.SecurityConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.neoflex.microservices.carpark.commons.config.JwtConfig;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Приложение работы с предзаказами.
 *
 * @author Denis_Begun
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
