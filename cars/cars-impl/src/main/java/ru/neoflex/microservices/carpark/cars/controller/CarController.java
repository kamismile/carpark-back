package ru.neoflex.microservices.carpark.cars.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.access.feign.AccessExpressionFeign;
import ru.neoflex.microservices.carpark.cars.api.CarApi;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.service.CarService;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CarController implements CarApi {

    private CarService carService;
    private LifecycleService lifecycleService;



    @Override
    @GetMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostFilter("hasPermission(filterObject, {'getCars_filter', {}})")
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    @Override
    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}} ,{'getCar'})")
    public Car getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @Override
    @PutMapping (value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'car', #car}} , {'updateCar'})")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return carService.updateCar(car);
    }

    @Override
    @PostMapping (value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'car', #car}} , {'createCar'})")
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @Override
    @DeleteMapping(value = "/cars/{id}")
    @PreAuthorize("hasPermission({{'id', #id}} , {'deleteCar'})")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
    }

    @Override
    @PatchMapping (value = "/cars/{id}/{event}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'stringEvent', #stringEvent}}, {'changeCarState'})")
    public Car changeCarState(@PathVariable Long id, String stringEvent) {
        Events event = Events.fromString(stringEvent);
        Car car = carService.getCar(id);
        States result = lifecycleService.doTransition(car, event);
        car.setState(result);
        car.setCurrentStatus(result.name().toLowerCase());
        car.setCurrentStatusDate(new Date());
        car = carService.updateCar(car);
        return car;
    }

}
