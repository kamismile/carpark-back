/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import ru.vtb.microservices.carpark.employees.api.EmployeeApi;

/**
 *  Feign client for employee api.
 *
 *  @author Roman_Morenko
 */
@FeignClient(name = "employees")
public interface EmployeeFeign extends EmployeeApi {
}
