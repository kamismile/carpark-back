/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import ru.vtb.microservices.carpark.dicts.api.DictsApi;

/**
 * Feign client for dicts.
 *
 * @author Roman_Morenko
 */
@FeignClient(name = "dicts")
public interface DictsFeign extends DictsApi {

}
