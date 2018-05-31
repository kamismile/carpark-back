package ru.neoflex.microservices.carpark.access.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import ru.neoflex.microservices.carpark.access.service.AccessExpressionResource;

/**
 * @autor rmorenko.
 */
@FeignClient(name ="acces-controll")
public interface AccessExpressionFeign extends AccessExpressionResource {



}
