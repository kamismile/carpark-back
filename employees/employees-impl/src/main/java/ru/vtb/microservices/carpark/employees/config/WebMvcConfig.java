package ru.vtb.microservices.carpark.employees.config;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilterResolver;
import ru.vtb.microservices.carpark.employees.model.LocationFilterResolver;

import java.util.List;

/**
 * WebMvc config.
 *
 * @author morenko
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public EmployeeFilterResolver employeeFilterResolver() {
        EmployeeFilterResolver employeeFilterResolver = new EmployeeFilterResolver();
        employeeFilterResolver.setObjectMapper(new ObjectMapper());
        return  employeeFilterResolver;
    }


    @Bean
    public LocationFilterResolver locationFilterResolver() {
        LocationFilterResolver locationFilterResolver = new LocationFilterResolver();
        locationFilterResolver.setObjectMapper(new ObjectMapper());
        return  locationFilterResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.employeeFilterResolver());
        argumentResolvers.add(this.locationFilterResolver());
    }

}
