package ru.vtb.microservices.carpark.employees.service;

import org.springframework.data.domain.PageRequest;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;

import java.util.List;

/**
 * Service for employee.
 *
 * @author mirzoevnik
 */
public interface EmployeeService {

    Employee getById(Long id);

    void deactivate(Long userId);

    Employee add(Employee employee);

    void update(Employee employee);

    List<Employee> getAll(EmployeeFilter filter);

    PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest);
}
