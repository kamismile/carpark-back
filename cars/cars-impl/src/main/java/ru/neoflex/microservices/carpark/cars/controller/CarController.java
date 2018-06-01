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
import ru.neoflex.microservices.carpark.cars.model.CarCommand;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.service.CarService;
import ru.neoflex.microservices.carpark.cars.service.KafkaService;
import ru.neoflex.microservices.carpark.cars.service.KafkaServiceImpl;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CarController implements CarApi {

    private CarService carService;
    private LifecycleService lifecycleService;
    private KafkaService kafkaService;

    @Override
    @GetMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostFilter("hasPermission(filterObject, {'getCars_filter', {}})")
    public List<Car> getCars(UserInfo userInfo) {
        return carService.getAllCars();
    }

    @Override
    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}} ,{'getCar'})")
    public Car getCar(UserInfo userInfo, @PathVariable Long id) {
        System.out.println(userInfo);
        Car car = carService.getCar(id);
        sendCommand(userInfo, car, Command.UPDATE);
        return carService.getCar(id);
    }

    @Override
    @PutMapping (value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'car', #car}} , {'updateCar'})")
    public Car updateCar(UserInfo userInfo, @PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        Car mergedCar = carService.updateCar(car);
        sendCommand(userInfo, mergedCar, Command.UPDATE);
        return mergedCar;
    }

    @Override
    @PostMapping (value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'car', #car}} , {'createCar'})")
    public Car createCar(UserInfo userInfo, @RequestBody Car car) {
        Car persistedCar = carService.createCar(car);
        sendCommand(userInfo, persistedCar, Command.ADD);
        return persistedCar;
    }

    @Override
    @DeleteMapping(value = "/cars/{id}")
    @PreAuthorize("hasPermission({{'id', #id}} , {'deleteCar'})")
    public void deleteCar(UserInfo userInfo, @PathVariable Long id) {
        Car car = carService.getCar(id);
        sendCommand(userInfo, car, Command.DELETE);
        carService.deleteById(id);
    }

    @Override
    @PatchMapping (value = "/cars/{id}/{event}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'stringEvent', #stringEvent}}, {'changeCarState'})")
    public Car changeCarState(UserInfo userInfo, @PathVariable Long id, String stringEvent) {
        Events event = Events.fromString(stringEvent);
        Car car = carService.getCar(id);
        States result = lifecycleService.doTransition(car, event);
        car.setState(result);
        car.setCurrentStatus(result.name().toLowerCase());
        car.setCurrentStatusDate(new Date());
        car = carService.updateCar(car);
        sendCommand(userInfo, car, Command.ADD);
        return car;
    }

    private void sendCommand (UserInfo userInfo, Car car, Command command) {
        CarCommand cc = new CarCommand();
        cc.setCommand(Command.UPDATE);
        cc.setEntity(car);
        cc.setMessageDate(new Date());
        cc.setUserInfo(userInfo);
        kafkaService.sendMessage(cc);
    }

}
