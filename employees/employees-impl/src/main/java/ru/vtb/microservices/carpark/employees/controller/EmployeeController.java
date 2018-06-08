package ru.vtb.microservices.carpark.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.employees.api.EmployeeApi;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;
import ru.vtb.microservices.carpark.employees.service.EmployeeService;

import java.util.List;

/**
 * Controller for employee.
 *
 * @author mirzoevnik
 */
@RestController
public class EmployeeController implements EmployeeApi {

    public static final String[] FIELDS = {"id", "name", "surname", "patronymic", "passportSeries",
            "passportNumber", "birthday", "position",
            "appointmentDate", "location.id", "location.address", "location.locationType",
            "location.active", "user.id", "user.passord", "user.role", "user.locationId",
            "user.active", "active"};

    public static final String[] FIELDS_FILTER = {"name", "surname", "patronymic", "positions", "appointmentDateFrom",
            "appointmentDateTo", "locations", "userId",
            "active"};

    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @Override
    public void deactivate(@PathVariable Long id) {
        employeeService.deactivate(id);
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

    @Override
    public String[] getFieldsName(EmployeeFilter filter, PageRequest pageRequest) {
        return FIELDS;
    }

    @Override
    public String[] getFieldsFilterName(EmployeeFilter filter, PageRequest pageRequest) {
        return FIELDS_FILTER;
    }


}
