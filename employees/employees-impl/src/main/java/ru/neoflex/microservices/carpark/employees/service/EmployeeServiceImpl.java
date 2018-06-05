package ru.neoflex.microservices.carpark.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.commons.dto.PageResponse;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.employees.dto.EmployeeCommand;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilter;
import ru.neoflex.microservices.carpark.employees.repository.EmployeeRepository;
import ru.neoflex.microservices.carpark.employees.sender.Sender;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.*;

/**
 * @author mirzoevnik
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final Sender sender;

    @Value("${kafka.topic.employee}")
    String employeeTopic;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Sender sender) {
        this.employeeRepository = employeeRepository;
        this.sender = sender;
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
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setCommand(Command.DELETE);
        employeeCommand.setEntity(employee);
        employeeCommand.setMessageDate(new Date());
        sender.send(employeeTopic, employeeCommand);
    }

    @Override
    public void add(Employee employee) {
        employeeRepository.save(employee);
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setCommand(Command.ADD);
        employeeCommand.setEntity(employee);
        employeeCommand.setMessageDate(new Date());
        sender.send(employeeTopic, employeeCommand);

    }

    @Override
    public void update(Employee employee) {
        Employee oldEmployee = employeeRepository.findOne(employee.getId());
        employeeRepository.save(employee);
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setCommand(Command.UPDATE);
        employeeCommand.setEntity(employee);
        employeeCommand.setOldEntity(oldEmployee);
        employeeCommand.setMessageDate(new Date());
        sender.send(employeeTopic, employeeCommand);
    }

    @Override
    public List<Employee> getAll(EmployeeFilter filter) {

        return employeeRepository.findAll(where(employeeLikeName(filter))
                .and(employeeLikeSurname(filter))
                .and(employeeLikePatronymic(filter))
                .and(employeeAfterAppointmentDate(filter))
                .and(employeeBeforeAppointmentDate(filter))
                .and(employeeIsActive(filter))
                .and(employeeHasUserId(filter))
                .and(employeeInLocations(filter))
                .and(employeeInPositions(filter)));
    }

    @Override
    public PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest) {
        Page<Employee> page =  employeeRepository.findAll(where(employeeLikeName(filter))
                .and(employeeLikeSurname(filter))
                .and(employeeLikePatronymic(filter))
                .and(employeeAfterAppointmentDate(filter))
                .and(employeeBeforeAppointmentDate(filter))
                .and(employeeIsActive(filter))
                .and(employeeHasUserId(filter)), pageRequest);
        return new PageResponse<>(page.getContent(), page.getTotalElements());
    }


}
