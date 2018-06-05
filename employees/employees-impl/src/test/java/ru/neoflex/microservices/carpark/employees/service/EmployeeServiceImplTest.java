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
import org.testng.annotations.*;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.repository.EmployeeRepository;
import ru.neoflex.microservices.carpark.employees.sender.Sender;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilter;
import ru.neoflex.microservices.carpark.employees.dto.EmployeeCommand;
import org.springframework.data.domain.PageRequest;
import ru.neoflex.microservices.carpark.commons.dto.PageResponse;
import ru.neoflex.microservices.carpark.commons.model.Command;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class EmployeeServiceImplTest {

	private static final String USER_ID = "userId";
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
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeCommand employeeCommand;
    private List<Employee> employeeList;
    EmployeeFilter filter;

    @BeforeMethod
    public void setupMock() {
        reset(employeeRepository);
        reset(sender);
        employeeService = new EmployeeServiceImpl(employeeRepository, sender);
        employee = new Employee();
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
    public void testGetByUserId() {
        when(employeeService.getByUserId(anyString())).thenReturn(employee);
        when(employeeService.getByUserId(null)).thenReturn(null);

        Employee employeeAny = employeeService.getByUserId(USER_ID);
        Employee employeeNull = employeeService.getByUserId(null);

        assertNotNull(employeeAny);
        assertNull(employeeNull);
        assertEquals(employeeAny.getName(), USER_NAME);
        verify(employeeRepository, atLeastOnce()).getByUserId(eq(USER_ID));
        verify(employeeRepository, atMost(1)).getByUserId(eq(null));
    }

    @Test
    public void testDeactivate() {
    	when(employeeService.getByUserId(anyString())).thenReturn(employee);
        when(employeeService.getByUserId(null)).thenReturn(null);
        doNothing().when(employeeRepository).delete(isA(Employee.class));
        doNothing().when(sender).send(isA(String.class), isA(EmployeeCommand.class));

        employeeService.deactivate(USER_ID);

        verify(employeeRepository, atLeastOnce()).getByUserId(eq(USER_ID));
        verify(employeeRepository, atLeastOnce()).delete(eq(employee));
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
    	when(employeeRepository.findAll(where(employeeLikeName(filter))
                .and(employeeLikeSurname(filter))
                .and(employeeLikePatronymic(filter))
                .and(employeeAfterAppointmentDate(filter))
                .and(employeeBeforeAppointmentDate(filter))
                .and(employeeIsActive(filter))
                .and(employeeHasUserId(filter))
                .and(employeeInLocations(filter))
                .and(employeeInPositions(filter)))).thenReturn(employeeList);

    	assertNotNull(employeeService.getAll(filter));
    }

}