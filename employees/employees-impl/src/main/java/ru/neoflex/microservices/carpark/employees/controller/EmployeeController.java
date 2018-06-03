package ru.neoflex.microservices.carpark.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.employees.api.EmployeeApi;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.dto.EmployeeCommand;
import ru.neoflex.microservices.carpark.employees.sender.Sender;
import ru.neoflex.microservices.carpark.employees.service.EmployeeService;

import java.util.List;

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
    public Employee getByUserId(String userId) {
        return employeeService.getByUserId(userId);
    }

    @Override
    public void deactivate(String userId) {
        employeeService.deactivate(userId);
    }

    @Override
    public void add(Employee employee) {
        employeeService.add(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeService.update(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeService.getAll();
    }


}
