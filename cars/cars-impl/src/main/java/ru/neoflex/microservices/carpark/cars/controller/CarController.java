package ru.neoflex.microservices.carpark.cars.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.cars.api.CarApi;
import ru.neoflex.microservices.carpark.cars.model.*;
import ru.neoflex.microservices.carpark.cars.service.CarService;
import ru.neoflex.microservices.carpark.cars.service.KafkaProducerService;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Slf4j
@RestController
public class CarController implements CarApi {

    private CarService carService;
    private LifecycleService lifecycleService;
    private KafkaProducerService kafkaProducerService;

    @Override
    public List<Car> getCars(UserInfo userInfo, CarFilter carFilter) {
        List<Car> list = carService.getAllCars(carFilter);
        list.stream().forEach(car -> {
            car.setAvailableEvents(lifecycleService.getAvailableTransitions(car));
        });
        return list;
    }

    @Override
    public Car getCar(UserInfo userInfo, Long id) {
        System.out.println(userInfo);
        Car car = carService.getCar(id);
        car.setAvailableEvents(lifecycleService.getAvailableTransitions(car));
        return carService.getCar(id);
    }

    @Override
    public Car updateCar(UserInfo userInfo, Long id, Car car) {
        car.setId(id);
        Car mergedCar = carService.updateCar(car);
        sendCommand(userInfo, mergedCar, Command.UPDATE);
        return mergedCar;
    }

    @Override
    public Car createCar(UserInfo userInfo, Car car) {
        Car persistedCar = carService.createCar(car);
        sendCommand(userInfo, persistedCar, Command.ADD);
        return persistedCar;
    }

    @Override
    public void deleteCar(UserInfo userInfo, Long id) {
        Car car = carService.getCar(id);
        carService.deleteById(id);
        sendCommand(userInfo, car, Command.DELETE);
    }

    @Override
    public Car changeCarState(UserInfo userInfo, Long id, String stringEvent) {
        Events event = Events.fromString(stringEvent);
        Car car = carService.getCar(id);
        States result = lifecycleService.doTransition(car, event);
        car.setState(result);
        car.setCurrentStatus(result.getStatusCode());
        car.setCurrentStatusDate(new Date());
        car = carService.updateCar(car);
        sendCommand(userInfo, car, Command.ADD);
        return car;
    }

    private void sendCommand(UserInfo userInfo, Car car, Command command) {
        CarCommand cc = new CarCommand();
        cc.setCommand(command);
        cc.setEntity(car);
        cc.setMessageDate(new Date());
        cc.setUserInfo(userInfo);
        kafkaProducerService.sendMessage(cc);
    }

}
