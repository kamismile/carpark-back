package ru.vtb.microservices.carpark.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.repository.CarRepository;

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

public class CarServiceImplTest {

    private static final Long ID = 1L;
    private static final String MARK = "Волга";
    private static final int YEAR = 2018;
    private static final int YEAR2 = 2017;
    private static final double MILEAGE = 1432.2d;
    private static final int DAY = 15;
    private CarFilter carFilter;
    private Car car;

    @Autowired
    private CarRepository carRepository = mock(CarRepository.class);
    @Autowired
    private KafkaProducerService kafkaProducerService = mock(KafkaProducerService.class);
    @Autowired
    private CarService carService;

    @BeforeClass
    public void setUp() {
        reset(carRepository);
        reset(kafkaProducerService);
        carService = new CarServiceImpl(carRepository, kafkaProducerService);
        carFilter = new CarFilter();
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
                .and(carInMarks(carFilter)))).thenReturn(Arrays.asList(car));

        Assert.assertNotNull(carService.getAllCars(carFilter));
    }
}
