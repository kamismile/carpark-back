package ru.neoflex.microservices.carpark.employees.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.neoflex.microservices.carpark.employees.model.Employee;

/**
 * @author mirzoevnik
 */
public interface EmployeeService {

    Employee getByUserId(String userId);

    void delete(String userId);

    void addUpdate(Employee employee);

}
