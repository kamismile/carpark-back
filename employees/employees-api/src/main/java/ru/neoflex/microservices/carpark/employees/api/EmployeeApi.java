package ru.neoflex.microservices.carpark.employees.api;

import ru.neoflex.microservices.carpark.employees.model.Employee;

/**
 * @author mirzoevnik
 */
public interface EmployeeApi {

    Employee getByUserId(String userId);
}
