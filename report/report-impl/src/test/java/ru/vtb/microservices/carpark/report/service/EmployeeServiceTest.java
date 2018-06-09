package ru.vtb.microservices.carpark.report.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.report.model.EmployeeCommand;
import ru.vtb.microservices.carpark.report.repository.EmployeeRepository;
import ru.vtb.microservices.carpark.report.repository.UserInfoRepository;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

public class EmployeeServiceTest {

    private MockMvc mockMvc;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private UserInfoRepository userInfoRepository;
    @InjectMocks
    private EmployeeService employeeService;

    private Long USER_ID = 111111l;
    private String USER_LOGIN = "login";
    private String EXCEPTION = "exception";
    private Long EMPLOYEE_ID = 222222l;
    private Employee employee;
    private EmployeeCommand employeeCommand;
    private UserInfo userInfo;

    @BeforeMethod
    public void setupMock() {
        userInfo = getUserInfo(USER_LOGIN, USER_ID);
        employee = getEmployee(userInfo);
        employeeCommand = getEmployeeCommand(Command.ADD, employee);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeService).build();
        reset(employeeRepository);
        reset(userInfoRepository);
    }

    @Test
    public void testSave() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.findByUserLogin(anyString())).thenReturn(employee);
        when(employeeRepository.findByUserLogin(EXCEPTION)).thenThrow(RuntimeException.class);
        when(userInfoRepository.save(userInfo)).thenReturn(userInfo);
        employeeService.save(employeeCommand);
        verify(employeeRepository, atLeastOnce()).save(eq(employee));
        verify(employeeRepository, atLeastOnce()).findByUserLogin(eq(USER_LOGIN));
        verify(userInfoRepository, atLeastOnce()).save(eq(userInfo));
        userInfo = getUserInfo(EXCEPTION, USER_ID);
        employee = getEmployee(userInfo);
        employeeCommand = getEmployeeCommand(Command.DELETE, employee);
        employeeService.save(employeeCommand);
        verify(employeeRepository, atLeastOnce()).save(eq(employee));
        verify(employeeRepository, atLeastOnce()).findByUserLogin(eq(EXCEPTION));
        verify(userInfoRepository, atLeastOnce()).save(eq(userInfo));
    }

    private UserInfo getUserInfo(String login, Long id) {
        userInfo = new UserInfo();
        userInfo.setId(USER_ID);
        userInfo.setLogin(login);
        return userInfo;
    }

    private Employee getEmployee(UserInfo userInfo) {
        employee = new Employee();
        employee.setId(EMPLOYEE_ID);
        employee.setUser(userInfo);
        return employee;
    }

    private EmployeeCommand getEmployeeCommand(Command command,  Employee employee) {
        employeeCommand = new EmployeeCommand();
        employeeCommand.setEntity(employee);
        employeeCommand.setOldEntity(employee);
        employeeCommand.setCommand(command);
        return employeeCommand;
    }

}