/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



/**
 * Config for swager.
 *
 * @author Vadim_Anosov
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.vtb.microservices.carpark.dicts.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }


    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "CarPark REST API", "Spring Boot REST API for Dicts","2.0", "", "", "", "");
        return apiInfo;
    }
}
