package ru.neoflex.microservices.carpark.employees.service;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeAfterAppointmentDate;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeBeforeAppointmentDate;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeHasUserId;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeInLocations;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeInPositions;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeIsActive;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeLikePatronymic;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeLikeSurname;
import static ru.neoflex.microservices.carpark.employees.repository.EmployeeSprecifications.employeeLikeName;

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



/**
 * Service for employee.
 *
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
    UserInfoService userInfoService;

    @Autowired
    LocationService locationService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Sender sender) {
        this.employeeRepository = employeeRepository;
        this.sender = sender;
    }

    @Override
    public Employee getByUserId(Long userId) {
        return employeeRepository.getByUserId(userId);
    }

    @Override
    public void deactivate(Long userId) {
        Employee employee = employeeRepository.getByUserId(userId);
        employee.setActive(false);
        employeeRepository.save(employee);
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setCommand(Command.DELETE);
        employeeCommand.setEntity(employee);
        employeeCommand.setMessageDate(new Date());
        if (employee.getUser() != null) {
            employee.getUser().setActive(false);
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
        if (employee.getUser()!= null && employee.getUser().getId()==null){
            Long employeeId = userInfoService.addUserInfo(employee.getUser()).getId();
            employee.getUser().setId(employeeId);
        }
        if (employee.getLocation()!= null && employee.getLocation().getId()==null){
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
