/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import ru.vtb.microservices.carpark.commons.config.JwtConfig;
import ru.vtb.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.vtb.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.vtb.microservices.carpark.commons.config.SecurityConfig;
/**
 * Main class.
 *
 *@author Roman_Mmorenko
 */

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@Import({MethodSecurityConfig.class, SecurityConfig.class, JwtWebMvcConfig.class, JwtConfig.class })
public class AccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessApplication.class, args);
    }
}
