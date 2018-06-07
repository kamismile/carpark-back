/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.access.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import ru.vtb.microservices.carpark.access.service.AccessExpressionResource;

/**
 * Client for external call access-controll.
 * @author Roman_Morenko
 */
@FeignClient(name = "acces-controll")
public interface AccessExpressionFeign extends AccessExpressionResource {
}