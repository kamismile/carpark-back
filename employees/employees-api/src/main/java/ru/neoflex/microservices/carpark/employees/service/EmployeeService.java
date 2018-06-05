package ru.neoflex.microservices.carpark.employees.service;

import org.springframework.data.domain.PageRequest;
import ru.neoflex.microservices.carpark.commons.dto.PageResponse;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilter;

import java.util.List;

/**
 * Service for employee.
 *
 * @author mirzoevnik
 */
public interface EmployeeService {

    Employee getByUserId(String userId);

    void deactivate(String userId);

    Employee add(Employee employee);

    void update(Employee employee);

    List<Employee> getAll(EmployeeFilter filter);

    PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest);
}
