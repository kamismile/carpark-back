package ru.neoflex.microservices.carpark.cars.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.cars.CarsApplication;
import ru.neoflex.microservices.carpark.cars.controller.CarController;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.service.CarService;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(classes = {CarsApplication.class})
@ActiveProfiles("development")
@WebAppConfiguration
public class CarsIntegrationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CarService carService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeSuite
    public void setup() {
    }

    @Test
    public void testMVC () throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mvc.perform(get("/cars/"))
                .andDo(print());
                //.andExpect(jsonPath("$.length()").value(4))
                //.andExpect(jsonPath("$.[1].mark").value("Волга"))
                //.andExpect(jsonPath("$.[1].currentStatusDate").value("2018-06-15"));
    }

    @Test
    public void carRepoTest() {
        Car car = getBaseCar();
        Car car2 = carService.createCar(car);
        Assert.assertEquals(car, car2);
        //this will fail with test data
        Assert.assertEquals(car2.getId().longValue(), 1l);
        carService.deleteById(1l);
    }

    @Test
    public void carsListTest() {
        Car car = getBaseCar();
        Car car2 = getBaseCar();
        Car car3 = getBaseCar();
        Car car4 = getBaseCar();
        carService.createCar(car);
        carService.createCar(car2);
        carService.createCar(car3);
        carService.createCar(car4);

        List<Car> list = carService.getAllCars();
        //this will fail with test data
        Assert.assertEquals(list.size(),4);
    }

    private Car getBaseCar() {
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
