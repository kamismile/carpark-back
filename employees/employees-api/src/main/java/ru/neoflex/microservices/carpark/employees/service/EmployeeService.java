package ru.neoflex.microservices.carpark.employees.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.neoflex.microservices.carpark.commons.dto.PageResponse;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilter;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface EmployeeService {

    Employee getByUserId(String userId);

    void deactivate(String userId);

    void add(Employee employee);

    void update(Employee employee);

    List<Employee> getAll(EmployeeFilter filter);

    PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest);
}
