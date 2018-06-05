package ru.neoflex.microservices.carpark.cars.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.cars.CarsApplication;
import ru.neoflex.microservices.carpark.cars.exception.TransitionUnsupportedException;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;

import java.util.List;

@SpringBootTest(classes = {CarsApplication.class})
@ActiveProfiles("development")
public class LifecycleIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private LifecycleService lifecycleService;

    @Test
    public void injectionTest() {
        Assert.assertNotNull(lifecycleService, "LifecycleService not autoinjected properly");
    }

    @Test
    public void availableTransitionsForActiveStateTest() {
        Car car = new Car();
        car.setState(States.READY);
        List<Events> list = lifecycleService.getAvailableTransitions(car);
        Assert.assertFalse(list.isEmpty(), "No transitions returned for state ACTIVE");
        Assert.assertEquals(list.size(), 2, "Wrong number of transitions returned for state ACTIVE");
        Assert.assertTrue(list.contains(Events.RENT), "Event RENT not returned for state ACTIVE");
        Assert.assertTrue(list.contains(Events.SERVICE), "Event SERVICE not returned for state ACTIVE");
    }

    @Test
    public void availableTransitionsForActiveStateTest2() {
        Car car = new Car();
        car.setState(States.IN_SERVICE);
        List<Events> list = lifecycleService.getAvailableTransitions(car);
        Assert.assertFalse(list.isEmpty(), "No transitions returned for state ACTIVE");
        Assert.assertEquals(list.size(), 2, "Wrong number of transitions returned for state ACTIVE");
        Assert.assertTrue(list.contains(Events.RETURN), "Event RETURN not returned for state ACTIVE");
        Assert.assertTrue(list.contains(Events.RETIRE), "Event RETIRE not returned for state ACTIVE");
    }

    @Test
    public void transitionSuccessfulInitialStateTest() {
        Car car = new Car();
        car.setState(States.READY);
        Assert.assertEquals(lifecycleService.
                doTransition(car, Events.RENT), States.IN_USE, "Could not make transition from ACTIVE with RENT event");
    }

    @Test(expectedExceptions = TransitionUnsupportedException.class)
    public void transitionUnsuccessfulTest() {
        Car car = new Car();
        car.setState(States.READY);
        lifecycleService.
                doTransition(car, Events.RETIRE);
    }

    @Test
    public void transitionSuccessfulNotInitialStateTest() {
        Car car = new Car();
        car.setState(States.IN_SERVICE);
        Assert.assertEquals(lifecycleService.
                doTransition(car, Events.RETIRE), States.DECOMMISSIONED, "Could not make transition from IN_SERVICE with RETIRE event");

    }



}
