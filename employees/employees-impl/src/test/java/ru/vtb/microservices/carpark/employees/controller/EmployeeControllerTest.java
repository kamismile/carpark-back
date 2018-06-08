package ru.vtb.microservices.carpark.employees.controller;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.employees.dto.EmployeeCommand;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;
import ru.vtb.microservices.carpark.employees.service.EmployeeService;

public class EmployeeControllerTest {

    private static final Long USER_ID = 1L;
    private static Long EMPLOYEE_ID = 111l;
    private static final String PASSPORT_SERIES = "1111";
    private static final String PASSPORT_NUMBER = "222222";
    private static final String USER_NAME = "Gosha";
    private static final String USER_SURNAME = "Kucenko";

    @Autowired
    private EmployeeService employeeService = mock(EmployeeService.class);
    @Autowired
    private EmployeeController employeeController;

    private Employee employee;
    private EmployeeCommand employeeCommand;
    private List<Employee> employeeList;
    private EmployeeFilter filter;
    private UserInfo userInfo;

    @BeforeMethod
    public void setupMock() {
        reset(employeeService);
        employeeController = new EmployeeController(employeeService);
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
        when(employeeService.getById(anyLong())).thenReturn(employee);
        Employee employeeAny = employeeController.getById(USER_ID);
        assertNotNull(employeeAny);
        assertEquals(employeeAny.getName(), USER_NAME);
        verify(employeeService, atLeastOnce()).getById(eq(USER_ID));
    }

    @Test
    public void testDeactivate() {
        doNothing().when(employeeService).deactivate(isA(Long.class));
        employeeController.deactivate(USER_ID);
        verify(employeeService, atLeastOnce()).deactivate(eq(USER_ID));
    }

    @Test
    public void testAdd() {
        when(employeeService.add(employee)).thenReturn(employee);
        employeeController.add(employee);
        verify(employeeService, atLeastOnce()).add(eq(employee));
    }

    @Test
    public void testUpdate() {
        doNothing().when(employeeService).update(isA(Employee.class));
        employeeController.update(employee);
        verify(employeeService, atLeastOnce()).update(eq(employee));
    }

    @Test
    public void testGetAll() {
        when(employeeService.getAll(filter)).thenReturn(employeeList);
        employeeController.getAll(filter);
        assertNotNull(employeeService.getAll(filter));
    }
}