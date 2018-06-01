package ru.neoflex.microservices.carpark.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.employees.api.EmployeeApi;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.service.EmployeeService;

/**
 * @author mirzoevnik
 */
@RestController
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @GetMapping(value = "/employee/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByUserId(@PathVariable String userId) {
        return employeeService.getByUserId(userId);
    }
}
