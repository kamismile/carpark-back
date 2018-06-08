/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.vtb.microservices.carpark.cars.model.CarFilterResolver;

import java.util.List;

/**
 * WebMvc config.
 *
 * @author Roman_Morenko
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CarFilterResolver carFilterResolver() {
        CarFilterResolver carFilterResolver = new CarFilterResolver();
        carFilterResolver.setObjectMapper(new ObjectMapper());
        return carFilterResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.carFilterResolver());
    }

}
