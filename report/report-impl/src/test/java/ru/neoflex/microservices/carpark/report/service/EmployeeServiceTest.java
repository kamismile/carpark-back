package ru.neoflex.microservices.carpark.report.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
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

import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.report.model.EmployeeCommand;
import ru.neoflex.microservices.carpark.report.model.LocationCommand;
import ru.neoflex.microservices.carpark.report.repository.EmployeeRepository;
import ru.neoflex.microservices.carpark.report.repository.LocationRepository;
import ru.neoflex.microservices.carpark.report.repository.UserInfoRepository;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;

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
    private Long EMPLOYEE_ID = 222222l;
    private Employee employee;
    private EmployeeCommand employeeCommand;
    private UserInfo userInfo;

    @BeforeMethod
    public void setupMock() {
        userInfo = new UserInfo();
        userInfo.setId(USER_ID);
        userInfo.setLogin(USER_LOGIN);
        employee = new Employee();
        employee.setId(EMPLOYEE_ID);
        employee.setUser(userInfo);
        employeeCommand = new EmployeeCommand();
        employeeCommand.setEntity(employee);
        employeeCommand.setOldEntity(employee);
        employeeCommand.setCommand(Command.ADD);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeService).build();
        reset(employeeRepository);
        reset(userInfoRepository);
    }

    @Test
    public void testSave() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.findByUserLogin(anyString())).thenReturn(employee);
        when(userInfoRepository.save(userInfo)).thenReturn(userInfo);

        employeeService.save(employeeCommand);

        verify(employeeRepository, atLeastOnce()).save(eq(employee));
    }

}