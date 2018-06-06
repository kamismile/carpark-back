package ru.neoflex.microservices.carpark.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.cars.CarsApplication;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.repository.CarRepository;


import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CarsApplication.class)
public class CarControllerTest {

    @Autowired
    private CarController carController;

    @Autowired
    private CarRepository carRepository;

    private MockMvc mockMvc;
    private Car car;

    @BeforeMethod
    public void setUp() throws Exception {
        carController = mock(CarController.class);
        carRepository = mock(CarRepository.class);
        car = getDefaultCar();
        carRepository.save(car);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(carController)
                .build();
    }

    @Test
    public void getCarTest() throws Exception {
        mockMvc.perform(get("/cars/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.year", is(car.getYear())));
    }

    private Car getDefaultCar() {
        Car car = new Car();
        car.setCurrentStatus(States.READY.toString().toLowerCase());
        car.setState(States.READY);
        car.setCurrentStatusDate(Date.from(LocalDate.of( 2018 , Month.JUNE , 15 ).atStartOfDay(ZoneId.of("GMT")).toInstant()));
        car.setMark("Волга");
        car.setYear(2017);
        car.setMileage(1432.2d);
        car.setNextMaintenanceDate(new Date());
        car.setPrevMaintenanceDate(new Date());
        car.setNextStatus(States.IN_SERVICE.toString().toLowerCase());
        car.setNextStatusDate(new Date());
        return car;
    }
}