package ru.neoflex.microservices.carpark.employees.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import ru.neoflex.microservices.carpark.employees.api.EmployeeApi;

/**
 *  Feign client for employee api.
 *
 *  @author rmorenko.
 */
@FeignClient(name = "employees")
public interface EmployeeFeign extends EmployeeApi {
}
