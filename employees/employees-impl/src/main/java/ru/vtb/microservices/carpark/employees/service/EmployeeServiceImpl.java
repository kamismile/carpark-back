/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.service;

import static org.springframework.data.jpa.domain.Specifications.where;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.employees.dto.EmployeeCommand;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;
import ru.vtb.microservices.carpark.employees.repository.EmployeeRepository;
import ru.vtb.microservices.carpark.employees.sender.Sender;
import ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;



/**
 * Service for employee.
 *
 * @author Mirzoev_Nikita
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final Sender sender;

    @Value("${kafka.topic.employee}")
    String employeeTopic;


    UserInfoService userInfoService;


    LocationService locationService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Sender sender,
            UserInfoService userInfoService, LocationService locationService) {
        this.employeeRepository = employeeRepository;
        this.userInfoService = userInfoService;
        this.locationService = locationService;
        this.sender = sender;
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public void deactivate(Long id) {
        Employee employee = employeeRepository.findOne(id);
        employee.setActive(false);
        employeeRepository.save(employee);
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setCommand(Command.DELETE);
        employeeCommand.setEntity(employee);
        employeeCommand.setMessageDate(new Date());
        if (employee.getUser() != null) {
            employee.getUser().setActive(Boolean.FALSE);
            userInfoService.uppdateUserInfo(employee.getUser());
        }
        sender.send(employeeTopic, employeeCommand);
    }

    @Override
    public Employee add(Employee employee) {
        saveUserAndLocation(employee);
        employeeRepository.save(employee);
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setCommand(Command.ADD);
        employeeCommand.setEntity(employee);
        employeeCommand.setMessageDate(new Date());
        sender.send(employeeTopic, employeeCommand);
        return employee;
    }

    private void saveUserAndLocation(Employee employee) {
        if (employee.getUser() != null && employee.getUser().getId() == null) {
            Long employeeId = userInfoService.addUserInfo(employee.getUser()).getId();
            employee.getUser().setId(employeeId);
        }
        if (employee.getLocation() != null && employee.getLocation().getId() == null) {
            Long locationId = locationService.add(employee.getLocation()).getId();
            employee.getLocation().setId(locationId);
        }
    }

    @Override
    public void update(Employee employee) {
        Employee oldEmployee = employeeRepository.findOne(employee.getId());
        employeeRepository.save(employee);
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setOldEntity(oldEmployee);
        employeeCommand.setCommand(Command.UPDATE);
        employeeCommand.setEntity(employee);
        employeeCommand.setMessageDate(new Date());
        sender.send(employeeTopic, employeeCommand);
    }
    @SuppressWarnings("all")
    @Override
    public List<Employee> getAll(EmployeeFilter filter) {

        return employeeRepository.findAll(where(EmployeeSprecifications.employeeLikeName(filter))
                .and(EmployeeSprecifications.employeeLikeSurname(filter))
                .and(EmployeeSprecifications.employeeLikePatronymic(filter))
                .and(EmployeeSprecifications.employeeAfterAppointmentDate(filter))
                .and(EmployeeSprecifications.employeeBeforeAppointmentDate(filter))
                .and(EmployeeSprecifications.employeeIsActive(filter))
                .and(EmployeeSprecifications.employeeHasUserId(filter))
                .and(EmployeeSprecifications.employeeInLocations(filter))
                .and(EmployeeSprecifications.employeeInPositions(filter)));
    }
    @SuppressWarnings("all")
    @Override
    public PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest) {
        Page<Employee> page =  employeeRepository.findAll(where(EmployeeSprecifications.employeeLikeName(filter))
                .and(EmployeeSprecifications.employeeLikeSurname(filter))
                .and(EmployeeSprecifications.employeeLikePatronymic(filter))
                .and(EmployeeSprecifications.employeeAfterAppointmentDate(filter))
                .and(EmployeeSprecifications.employeeBeforeAppointmentDate(filter))
                .and(EmployeeSprecifications.employeeIsActive(filter))
                .and(EmployeeSprecifications.employeeHasUserId(filter)), pageRequest);
        return new PageResponse<>(page.getContent(), page.getTotalElements());
    }


}
