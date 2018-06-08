package ru.vtb.microservices.carpark.cars.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarCommand;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.repository.CarRepository;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.commons.model.Command;

import java.sql.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.vtb.microservices.carpark.cars.repository.CarSpecifications.*;
import static ru.vtb.microservices.carpark.cars.repository.CarSpecifications.carInMarks;
@Slf4j
public class CarServiceImplTest {

    private static final Long ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String MARK = "Волга";
    private static final String NAME = "Nikita";
    private static final String ROLE = "Administrator";
    private static final int YEAR = 2018;
    private static final int YEAR2 = 2017;
    private static final double MILEAGE = 1432.2d;
    private static final int DAY = 15;

    @Autowired
    private CarRepository carRepository = mock(CarRepository.class);
    @Autowired
    private KafkaProducerService kafkaProducerService = mock(KafkaProducerService.class);
    @Autowired
    private CarService carService;

    private CarFilter carFilter;
    private Car car;
    private UserInfo userInfo;

    @BeforeClass
    public void setUp() {
        reset(carRepository);
        reset(kafkaProducerService);
        carService = new CarServiceImpl(carRepository, kafkaProducerService);
        carFilter = new CarFilter();
        car = new Car();
        userInfo = new UserInfo();
        userInfo.setLocationId(USER_ID);
        userInfo.setName(NAME);
        userInfo.setRole(ROLE);
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
    }

    @Test
    public void testGetAllCars() {
        when(carRepository.findAll(where(carIsYearFrom(carFilter))
                .and(carIsYearTo(carFilter))
                .and(carInCurentStatuses(carFilter))
                .and(carIsCurrentLocationId(carFilter))
                .and(carIsMileageFrom(carFilter))
                .and(carIsMileageTo(carFilter))
                .and(carIsLocationId(carFilter))
                .and(carInMarks(carFilter)))).thenReturn(Arrays.asList(car)
        );

        Assert.assertNotNull(carService.getAllCars(carFilter));
    }

    @Test
    public void testGetCar() {
        when(carRepository.getOne(anyLong())).thenReturn(car);
        when(carRepository.getOne(null)).thenReturn(null);
        when(carService.getCar(anyLong())).thenReturn(car);
        when(carService.getCar(null)).thenReturn(null);

        Car carAny = carService.getCar(ID);
        Car carNull = carService.getCar(null);

        Assert.assertNotNull(carAny);
        Assert.assertNull(carNull);
        Assert.assertEquals(ID, carAny.getId());

        verify(carRepository, atMost(1)).getOne(eq(ID));
        verify(carRepository, atMost(1)).getOne(eq(null));
    }

    @Test
    public void testCreateCar() {
        when(carRepository.save(isA(Car.class))).thenReturn(car);
        doNothing().when(kafkaProducerService).sendMessage(isA(CarCommand.class));

        carService.createCar(userInfo, car);

        verify(carRepository, atLeastOnce()).save(eq(car));
    }

    @Test
    public void testUpdateCar() {
        when(carRepository.save(isA(Car.class))).thenReturn(car);
        doNothing().when(kafkaProducerService).sendMessage(isA(CarCommand.class));

        carService.updateCar(userInfo, car);

        verify(carRepository, atLeastOnce()).save(eq(car));
    }

    @Test
    public void testUpploadCar() {
        doNothing().when(kafkaProducerService).sendMessage(isA(CarCommand.class));
        carService.upploadCar(userInfo, car);
    }
}
