package ru.neoflex.microservices.carpark.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.repository.EmployeeRepository;

/**
 * @author mirzoevnik
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getByUserId(String userId) {
        return employeeRepository.getByUserId(userId);
    }

    @Override
    public void deactivate(String userId) {
        Employee employee = employeeRepository.getByUserId(userId);
        employee.setActive(false);
        employeeRepository.delete(employee);
    }

    @Override
    public void addUpdate(Employee employee) {
        employeeRepository.save(employee);
    }


}
