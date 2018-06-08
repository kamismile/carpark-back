/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Feign config.
 *
 * @author Roman_Morenko
 */
@Configuration
@EnableFeignClients("ru.vtb.microservices.carpark.access.feign")
public class FeignConfig {

}
