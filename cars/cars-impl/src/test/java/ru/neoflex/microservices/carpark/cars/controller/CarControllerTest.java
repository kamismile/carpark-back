package ru.neoflex.microservices.carpark.cars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.cars.CarController;
import ru.neoflex.microservices.carpark.cars.api.CarApi;
import ru.neoflex.microservices.carpark.cars.model.*;
import ru.neoflex.microservices.carpark.cars.service.CarService;
import ru.neoflex.microservices.carpark.cars.service.KafkaProducerService;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
@Slf4j
public class CarControllerTest {

    private static final Long ID = 1L;
    private static final Long UPDATE_ID = 2L;
    private static final String NAME = "Igor";
    private static final String ROLE = "Administrator";
    private static final String MARK = "Волга";
    private static final int CAR_LIST_SIZE = 1;
    private static final int YEAR = 2018;
    private static final int YEAR2 = 2017;
    private static final double MILEAGE = 1432.2d;
    private static final int DAY = 15;
    private UserInfo userInfo;
    private Events events;
    private States states;
    private Transition transition;
    private List<Car> carList;
    private Car car;
    private CarFilter carFilter;

    @Autowired
    private CarService carService = mock(CarService.class);
    @Autowired
    private LifecycleService lifecycleService = mock(LifecycleService.class);
    @Autowired
    private KafkaProducerService kafkaProducerService = mock(KafkaProducerService.class);
    @Autowired
    private CarApi carApi;

    @BeforeClass
    public void setUp() {
        reset(carService);
        reset(lifecycleService);
        reset(kafkaProducerService);
        carApi = new CarController(carService, lifecycleService, kafkaProducerService);
        userInfo = new UserInfo();
        car = new Car();
        car.setId(ID);
        car.setCurrentStatus(States.READY.toString().toLowerCase());
        car.setState(States.READY);
        car.setCurrentStatusDate(Date.from(LocalDate.of(YEAR, Month.JUNE, DAY)
                .atStartOfDay(ZoneId.of("GMT"))
                .toInstant()));
        car.setMark(MARK);
        car.setYear(YEAR2);
        car.setMileage(MILEAGE);
        car.setNextMaintenanceDate(new Date());
        car.setPrevMaintenanceDate(new Date());
        car.setNextStatus(States.IN_SERVICE.toString().toLowerCase());
        car.setNextStatusDate(new Date());
        carFilter = new CarFilter();
        userInfo.setLocationId(ID);
        userInfo.setName(NAME);
        userInfo.setRole(ROLE);
        carList = new ArrayList<>();
        carList.add(car);
    }

    @Test
    public void testGetCars() {
        when(carService.getAllCars(carFilter)).thenReturn(Arrays.asList(car));
        when(lifecycleService.getAvailableTransitions(car)).thenReturn(Arrays.asList(events));

        List<Car> carList = carApi.getCars(userInfo, carFilter);

        Assert.assertNotNull(carList.size());
        Assert.assertEquals(carList.size(), CAR_LIST_SIZE);

        Assert.assertEquals(carList.get(0).getId(), ID);
        Assert.assertEquals(carList.get(0).getMark(), MARK);

        verify(carService, atLeastOnce()).getAllCars(eq(carFilter));
        verify(lifecycleService, atLeastOnce()).getAvailableTransitions(eq(car));
    }

    @Test
    public void testGetCar() {
        when(carService.getCar(ID)).thenReturn(car);
        when(lifecycleService.getAvailableTransitions(car)).thenReturn(Arrays.asList(events));

        Car car = carApi.getCar(userInfo, ID);

        Assert.assertNotNull(car);
        Assert.assertEquals(ID, car.getId());
        Assert.assertEquals(MARK, car.getMark());

        verify(carService, atLeastOnce()).getCar(eq(ID));
        verify(lifecycleService, atLeastOnce()).getAvailableTransitions(eq(car));
    }

    @Test
    public void testUpdateCar() {
        car.setId(UPDATE_ID);
        when(carService.updateCar(isA(Car.class))).thenReturn(car);
        doNothing().when(kafkaProducerService).sendMessage(isA(CarCommand.class));

        Car carAny = carApi.updateCar(userInfo, UPDATE_ID, car);
        Assert.assertEquals(carAny.getId(), UPDATE_ID);

        verify(carService, atLeastOnce()).updateCar(eq(car));
    }

    @Test
    public void testCreateCar() {
        when(carService.createCar(isA(Car.class))).thenReturn(car);
        doNothing().when(kafkaProducerService).sendMessage(isA(CarCommand.class));

        Car carAny = carApi.createCar(userInfo, car);
        Assert.assertNotNull(carAny);
        Assert.assertEquals(ID, carAny.getId());

        verify(carService, atLeastOnce()).createCar(eq(car));
    }

    @Test
    public void testDeleteCar() {
        when(carService.getCar(ID)).thenReturn(car);
        doNothing().when(kafkaProducerService).sendMessage(isA(CarCommand.class));
        carApi.deleteCar(userInfo, ID);
        verify(carService, atLeastOnce()).createCar(eq(car));
    }

    @Test
    public void testChangeCarState() {
        events = Events.fromString(Events.RENT.name());
        states = States.DECOMMISSIONED;
        when(carService.getCar(ID)).thenReturn(car);
        when(lifecycleService.doTransition(isA(Car.class), isA(Events.class))).thenReturn(states);
        car.setCurrentStatus(states.getStatusCode());
        car.setCurrentStatusDate(new Date());
        car.setState(states);
        when(carService.updateCar(isA(Car.class))).thenReturn(car);
        doNothing().when(kafkaProducerService).sendMessage(isA(CarCommand.class));

        Car carAny = carApi.changeCarState(userInfo, ID, "RETIRE");
        Assert.assertEquals(car.getState(), States.DECOMMISSIONED);

        verify(carService, atLeastOnce()).getCar(eq(ID));
        verify(lifecycleService, atLeastOnce()).doTransition(eq(car), eq(Events.RETIRE));
        verify(carService, atLeastOnce()).updateCar(eq(car));
    }
}