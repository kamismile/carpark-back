package ru.neoflex.microservices.carpark.employees.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.neoflex.microservices.carpark.commons.dto.PageResolver;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilterResolver;

import java.util.List;

/**
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
    public PageResolver pageResolver() {
        PageResolver pageResolver = new PageResolver();
        pageResolver.setObjectMapper(new ObjectMapper());
        return  pageResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.employeeFilterResolver());
        argumentResolvers.add(this.pageResolver());
    }

}
