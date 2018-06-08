/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import ru.vtb.microservices.carpark.commons.config.FeignConfig;
import ru.vtb.microservices.carpark.commons.config.JwtConfig;
import ru.vtb.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.vtb.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.vtb.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.vtb.microservices.carpark.commons.config.SecurityConfig;

/**
 * Main class.
 * @author Vadim_Anosov
 */
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