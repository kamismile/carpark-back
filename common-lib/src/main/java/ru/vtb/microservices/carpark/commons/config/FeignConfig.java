package ru.vtb.microservices.carpark.commons.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author rmorenko
 */
@Configuration
@EnableFeignClients("ru.vtb.microservices.carpark.access.feign")
public class FeignConfig {

}
