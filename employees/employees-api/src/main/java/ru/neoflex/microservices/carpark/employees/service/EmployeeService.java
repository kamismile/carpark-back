package ru.neoflex.microservices.carpark.employees.service;

import ru.neoflex.microservices.carpark.employees.model.Employee;

/**
 * @author mirzoevnik
 */
public interface EmployeeService {

    Employee getByUserId(Long userId);
}
