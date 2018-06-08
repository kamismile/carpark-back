/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dict.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import ru.vtb.microservices.carpark.commons.config.JwtWebMvcConfig;
import ru.vtb.microservices.carpark.commons.config.OAuth2FeignAutoConfiguration;
import ru.vtb.microservices.carpark.commons.config.SecurityConfig;
import ru.vtb.microservices.carpark.commons.config.FeignConfig;
import ru.vtb.microservices.carpark.commons.config.MethodSecurityConfig;
import ru.vtb.microservices.carpark.commons.config.JwtConfig;



/**
 * Main class.
 * @author Vadim_Nadsonov
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