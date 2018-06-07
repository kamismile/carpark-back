package ru.neoflex.microservices.carpark.report.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.model.CarEvent;
import ru.neoflex.microservices.carpark.report.model.LocationCommand;
import ru.neoflex.microservices.carpark.report.repository.CarEventRepository;
import ru.neoflex.microservices.carpark.report.repository.EmployeeRepository;
import ru.neoflex.microservices.carpark.report.repository.LocationRepository;
import ru.neoflex.microservices.carpark.report.repository.UserInfoRepository;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;

import org.springframework.beans.BeanUtils;

public class CarEventResourceServiceTest {

    private MockMvc mockMvc;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CarEventRepository carEventRepository;
    @InjectMocks
    private CarEventResourceService carEventResourceService;

    private String TEST_DATA = "test";
    private String USER_NAME = "Vasya";
    private Double MILEAGE = 2018d;
    private Long EMPLOYEE_ID = 1111111l;
    private Long CAR_ID = 222222l;
    private Long LOCATION_ID = 3333333l;
    private Car car;
    private CarCommand carCommand;
    private UserInfo userInfo;
    private CarEvent carEvent;
    private Employee employee;
    private List<Events> availableEvents;

    @BeforeMethod
    public void setupMock() {
        userInfo = new UserInfo();
        userInfo.setName(USER_NAME);
        car = getDefaultCar();
        carCommand = new CarCommand();
        carCommand.setUserInfo(userInfo);
        carCommand.setEntity(car);
        carCommand.setOldEntity(car);
        carCommand.setCommand(Command.ADD);
        carCommand.setMessageDate(new Date());
        employee = new Employee();
        employee.setId(EMPLOYEE_ID);
        carEvent = new CarEvent();
        carEvent.setMessageDate(carCommand.getMessageDate());
        carEvent.setUserName(carCommand.getUserInfo().getName());
        carEvent.setEmployee(employee);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carEventResourceService).build();
        reset(employeeRepository);
        reset(carEventRepository);
    }

    @Test
    public void testSave() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.findByUserLogin(anyString())).thenReturn(employee);
        when(carEventRepository.save(carEvent)).thenReturn(carEvent);

        carEventResourceService.save(carCommand);

        verify(employeeRepository, atLeastOnce()).findByUserLogin(eq(USER_NAME));
    }

    private Car getDefaultCar() {
        Date now = new Date();
        availableEvents = new ArrayList<Events>();
        Car car = new Car();
        car.setId(CAR_ID);
        car.setMark(TEST_DATA);
        car.setMileage(MILEAGE);
        car.setPrevMaintenanceDate(now);
        car.setNextMaintenanceDate(now);
        car.setCurrentStatus(TEST_DATA);
        car.setCurrentStatusDate(now);
        car.setNextStatus(TEST_DATA);
        car.setNextStatusDate(now);
        car.setState(States.READY);
        car.setLocationId(LOCATION_ID);
        car.setCurrentLocationId(LOCATION_ID);
        car.setNumber(TEST_DATA);
        car.setAvailableEvents(availableEvents);
        return car;
    }

}