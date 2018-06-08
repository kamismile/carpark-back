package ru.vtb.microservices.carpark.employees.service;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeAfterAppointmentDate;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeBeforeAppointmentDate;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeHasUserId;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeInLocations;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeInPositions;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeIsActive;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeLikePatronymic;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeLikeSurname;
import static ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications.employeeLikeName;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.repository.EmployeeRepository;
import ru.vtb.microservices.carpark.employees.sender.Sender;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;
import ru.vtb.microservices.carpark.employees.dto.EmployeeCommand;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.employees.dto.EmployeeCommand;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;
import ru.vtb.microservices.carpark.employees.repository.EmployeeRepository;
import ru.vtb.microservices.carpark.employees.repository.EmployeeSprecifications;
import ru.vtb.microservices.carpark.employees.sender.Sender;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class EmployeeServiceImplTest {

    private static final Long USER_ID = 1L;
    private static Long EMPLOYEE_ID = 111l;
    private static final String PASSPORT_SERIES = "1111";
    private static final String PASSPORT_NUMBER = "222222";
    private static final String USER_NAME = "Gosha";
    private static final String USER_SURNAME = "Kucenko";
    private static final String employeeTopic = null;

    @Autowired
    private EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    @Autowired
    private Sender sender = mock(Sender.class);
    @Autowired
    private UserInfoService userInfoService = mock(UserInfoService.class);
    @Autowired
    private LocationService locationService = mock(LocationService.class);
    @Autowired
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeCommand employeeCommand;
    private List<Employee> employeeList;
    private EmployeeFilter filter;
    private UserInfo userInfo;

    @BeforeMethod
    public void setupMock() {
        reset(employeeRepository);
        reset(sender);
        reset(userInfoService);
        reset(locationService);
        employeeService = new EmployeeServiceImpl(employeeRepository, sender, userInfoService, locationService);
        userInfo = new UserInfo();
        userInfo.setId(EMPLOYEE_ID);
        employee = new Employee();
        employee.setUser(userInfo);
        employee.setName(USER_NAME);
        employee.setSurname(USER_SURNAME);
        employee.setPassportSeries(PASSPORT_SERIES);
        employee.setPassportNumber(PASSPORT_NUMBER);
        employeeList = new ArrayList<Employee>();
        employeeList.add(employee);
        employeeCommand = new EmployeeCommand();
        employeeCommand.setCommand(Command.DELETE);
        employeeCommand.setEntity(employee);
        employeeCommand.setMessageDate(new Date());
        filter = new EmployeeFilter();
    }

    @Test
    public void testGetById() {
        when(employeeRepository.findOne(anyLong())).thenReturn(employee);

        Employee employeeAny = employeeService.getById(USER_ID);

        assertNotNull(employeeAny);
        assertEquals(employeeAny.getName(), USER_NAME);
        verify(employeeRepository, atLeastOnce()).findOne(eq(USER_ID));
    }

    @Test
    public void testDeactivate() {
        when(employeeRepository.findOne(anyLong())).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        doNothing().when(employeeRepository).delete(isA(Employee.class));
        doNothing().when(sender).send(isA(String.class), isA(EmployeeCommand.class));
        doNothing().when(userInfoService).uppdateUserInfo(isA(UserInfo.class));

        employeeService.deactivate(USER_ID);

        verify(employeeRepository, atLeastOnce()).findOne(eq(USER_ID));
        verify(employeeRepository, atLeastOnce()).save(eq(employee));
    }

    @Test
    public void testAdd() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        doNothing().when(sender).send(isA(String.class), isA(EmployeeCommand.class));

        employeeService.add(employee);

        verify(employeeRepository, atLeastOnce()).save(eq(employee));
    }

    @Test
    public void testUpdate() {
        when(employeeRepository.findOne(anyLong())).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);

        employeeService.update(employee);

        verify(employeeRepository, atLeastOnce()).save(eq(employee));
    }

    @Test
    public void testGetAll() {
        when(employeeRepository.findAll(where(EmployeeSprecifications.employeeLikeName(filter)).and(EmployeeSprecifications.employeeLikeSurname(filter))
                .and(EmployeeSprecifications.employeeLikePatronymic(filter)).and(EmployeeSprecifications.employeeAfterAppointmentDate(filter))
                .and(EmployeeSprecifications.employeeBeforeAppointmentDate(filter)).and(EmployeeSprecifications.employeeIsActive(filter)).and(EmployeeSprecifications.employeeHasUserId(filter))
                .and(EmployeeSprecifications.employeeInLocations(filter)).and(EmployeeSprecifications.employeeInPositions(filter)))).thenReturn(employeeList);

        assertNotNull(employeeService.getAll(filter));
    }

}