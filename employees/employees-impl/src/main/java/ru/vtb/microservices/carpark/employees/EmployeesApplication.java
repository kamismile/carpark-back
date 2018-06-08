/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import ru.vtb.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.vtb.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.vtb.microservices.carpark.commons.config.JwtConfig;
import ru.vtb.microservices.carpark.commons.config.SecurityConfig;
import ru.vtb.microservices.carpark.commons.config.FeignConfig;
import ru.vtb.microservices.carpark.commons.config.JwtWebMvcConfig;

/**
 * Intial class.
 *
 * @author Mirzoev_Nikita
 */
@EnableEurekaClient
@SpringBootApplication
@Import({OAuth2FeignAutoConfiguration.class, FeignConfig.class, MethodSecurityConfig.class,
        SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }
}
