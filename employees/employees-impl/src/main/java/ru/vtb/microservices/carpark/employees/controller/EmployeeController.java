/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.employees.api.EmployeeApi;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;
import ru.vtb.microservices.carpark.employees.service.EmployeeService;

import java.util.List;

/**
 * Controller for employee.
 *
 * @author Mirzoev_Nikita
 */
@RestController
@Api(value = "employees", description = "Rest API for employees operations", tags = "Employees API")
public class EmployeeController implements EmployeeApi {

    private static final String[] FIELDS = {"id", "name", "surname", "patronymic", "passportSeries",
            "passportNumber", "birthday", "position",
            "appointmentDate", "location.id", "location.address", "location.locationType",
            "location.active", "user.id", "user.passord", "user.role", "user.locationId",
            "user.active", "active"};

    private static final String[] FIELDS_FILTER = {"name", "surname", "patronymic", "positions", "appointmentDateFrom",
            "appointmentDateTo", "locations", "userId",
            "active"};

    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get employee by id")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @DeleteMapping(value = "/employee/deactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deactivate a employee")
    public void deactivate(@PathVariable Long id) {
        employeeService.deactivate(id);
    }

    @PostMapping(value = "/employee/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new employee")
    public Employee add(@RequestBody  Employee employee) {
        return  employeeService.add(employee);
    }

    @PutMapping(value = "/employee/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a employee")
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all employees")
    public List<Employee> getAll(EmployeeFilter filter) {
        return employeeService.getAll(filter);
    }

    @GetMapping(value = "/employeespage", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get employees by filter and page")
    public PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest) {
        return employeeService.getAll(filter, pageRequest);
    }

    @GetMapping(value = "/employeespage/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get name of fields")
    public String[] getFieldsName(EmployeeFilter filter, PageRequest pageRequest) {
        return FIELDS.clone();
    }

    @GetMapping(value = "/employeespage/fieldsFilter", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get name of fields by filter")
    public String[] getFieldsFilterName(EmployeeFilter filter, PageRequest pageRequest) {
        return FIELDS_FILTER.clone();
    }
}
