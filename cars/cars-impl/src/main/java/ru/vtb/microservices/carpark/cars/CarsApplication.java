/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.commons.config.FeignConfig;
import ru.vtb.microservices.carpark.commons.config.JwtConfig;
import ru.vtb.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.vtb.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.vtb.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.vtb.microservices.carpark.commons.config.SecurityConfig;
import ru.vtb.microservices.carpark.cars.model.Car;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Приложение сервиса управления автомобилями.
 *
 * @author Denis_Begun
 */
@EnableEurekaClient
@SpringBootApplication
@EntityScan(basePackageClasses = Car.class)
@EnableHystrix
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
        SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class})
public class CarsApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
    }
}
