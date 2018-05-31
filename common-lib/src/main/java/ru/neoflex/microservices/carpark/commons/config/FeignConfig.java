package ru.neoflex.microservices.carpark.commons.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author rmorenko
 */
@Configuration
@EnableFeignClients("ru.neoflex.microservices.carpark.access.feign")
public class FeignConfig {

}
