package ru.neoflex.microservices.carpark.cars.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.neoflex.microservices.carpark.cars.model.CarFilterResolver;
import ru.neoflex.microservices.carpark.commons.dto.PageResolver;
import java.util.List;

/**
 * WebMvc config.
 *
 * @author morenko
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CarFilterResolver carFilterResolver() {
        CarFilterResolver carFilterResolver = new CarFilterResolver();
        carFilterResolver.setObjectMapper(new ObjectMapper());
        return  carFilterResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.carFilterResolver());
    }

}
