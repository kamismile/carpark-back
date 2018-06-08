/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.microservices.carpark.cars.api.CarApi;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.cars.service.CarService;
import ru.vtb.microservices.carpark.cars.service.LifecycleService;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;

import java.util.Date;
import java.util.List;

/**
 * Controller for cars.
 *
 * @author Denis_Begun
 */

@AllArgsConstructor
@Slf4j
@RestController
@Api(value = "cars", description = "Rest API for cars operations", tags = "Cars API")
public class CarController implements CarApi {

    private CarService carService;
    private LifecycleService lifecycleService;


    @GetMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all cars")
    public List<Car> getCars(UserInfo userInfo, CarFilter carFilter) {
        List<Car> list = carService.getAllCars(carFilter);
        list.forEach(car -> car.setAvailableEvents(lifecycleService.getAvailableTransitions(car)));
        return list;
    }

    @GetMapping(value = "/carspage/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get cars by page and filter")
    public PageResponse<Car> getCars(UserInfo userInfo, CarFilter carFilter, PageRequest pageRequest) {
        Page<Car> page = carService.getAllCars(userInfo,carFilter,pageRequest);
        page.getContent().forEach(car -> car.setAvailableEvents(lifecycleService.getAvailableTransitions(car)));
        return new PageResponse<>(page.getContent(), page.getTotalPages());
    }

    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get car by id")
    public Car getCar(UserInfo userInfo, @PathVariable(name = "id") Long id) {
        Car car = carService.getCar(id);
        car.setAvailableEvents(lifecycleService.getAvailableTransitions(car));
        return carService.getCar(id);
    }

    @PutMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update info about a car")
    public Car updateCar(UserInfo userInfo, @PathVariable(name = "id") Long id, @RequestBody Car car) {
        car.setId(id);
        return carService.updateCar(userInfo, car);
    }

    @PostMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new car")
    public Car createCar(UserInfo userInfo, @RequestBody Car car) {
        return carService.createCar(userInfo, car);
    }

    @DeleteMapping(value = "/cars/{id}")
    @ApiOperation(value = "Delete a car")
    public void deleteCar(UserInfo userInfo, @PathVariable(name = "id") Long id) {
        carService.deleteById(userInfo, id);
    }

    @PatchMapping(value = "/cars/{id}/{stringEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Change status of car")
    public Car changeCarState(UserInfo userInfo, @PathVariable(name = "id") Long id, @PathVariable(name = "stringEvent") String stringEvent) {
        Events event = Events.fromString(stringEvent);
        Car car = carService.getCar(id);
        States result = lifecycleService.doTransition(car, event);
        car.setState(result);
        car.setCurrentStatus(result.getStatusCode());
        car.setCurrentStatusDate(new Date());
        car = carService.updateCar(userInfo, car);
        return car;
    }

    @GetMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Upload all cars")
    public List<Car> uploadAllCars(UserInfo userInfo) {
        List<Car> cars =  carService.getAllCars(new CarFilter());
        for (Car car : cars) {
            carService.upploadCar(userInfo,car);
        }
        return  cars;
    }
}
