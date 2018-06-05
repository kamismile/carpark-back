package ru.neoflex.microservices.carpark.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.commons.dto.PageResponse;
import ru.neoflex.microservices.carpark.employees.api.EmployeeApi;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilter;
import ru.neoflex.microservices.carpark.employees.service.EmployeeService;

import java.util.List;

/**
 * Controller for employee.
 *
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
    public Employee getByUserId(String userId) {
        return employeeService.getByUserId(userId);
    }

    @Override
    public void deactivate(String userId) {
        employeeService.deactivate(userId);
    }

    @Override
    public Employee add(@RequestBody  Employee employee) {
        return  employeeService.add(employee);
    }

    @Override
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @Override
    public List<Employee> getAll(EmployeeFilter filter) {
        return employeeService.getAll(filter);
    }

    @Override
    public PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest) {
        return employeeService.getAll(filter, pageRequest);
    }


}
