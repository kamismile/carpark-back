/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.vtb.microservices.carpark.cars.repository.CarSpecifications.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarCommand;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.cars.repository.CarRepository;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.commons.model.Command;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Car service implementation.
 *
 * @autor Denis_Begun
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public List<Car> getAllCars(CarFilter filter) {
        return carRepository.findAll(where(carIsYearFrom(filter))
                .and(carIsYearTo(filter))
                .and(carInCurentStatuses(filter))
                .and(carIsCurrentLocationId(filter))
                .and(carIsMileageFrom(filter))
                .and(carIsMileageTo(filter))
                .and(carIsLocationId(filter))
                .and(carInMarks(filter))
        );
    }

    @Override
    public Page<Car> getAllCars(UserInfo userInfo, CarFilter filter, PageRequest pageRequest) {
        return carRepository.findAll(where(carIsYearFrom(filter))
                .and(carIsYearTo(filter))
                .and(carInCurentStatuses(filter))
                .and(carIsCurrentLocationId(filter))
                .and(carIsMileageFrom(filter))
                .and(carIsMileageTo(filter))
                .and(carIsLocationId(filter))
                .and(carInMarks(filter))
                .and(accessSpecifications(userInfo)), pageRequest
        );
    }

    @Override
    public Car getCar(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public Car createCar(UserInfo userInfo, Car car) {
        Car persistedCar = carRepository.save(car);
        sendCommand(userInfo, persistedCar, Command.ADD);
        return persistedCar;
    }

    @Override
    public Car updateCar(UserInfo userInfo, Car car) {
        Car mergedCar = carRepository.save(car);
        sendCommand(userInfo, mergedCar, Command.UPDATE);
        return mergedCar;
    }

    @Override
    public void upploadCar(UserInfo userInfo, Car car) {
        sendCommand(userInfo, car, Command.ADD);
    }

    @Override
    public void deleteById(UserInfo userInfo, Long id) {
        Car car = getCar(id);
        carRepository.delete(id);
        sendCommand(userInfo, car, Command.DELETE);
    }

    private void sendCommand(UserInfo userInfo, Car car, Command command) {
        CarCommand cc = new CarCommand();
        cc.setCommand(command);
        cc.setEntity(car);
        cc.setMessageDate(new Date());
        cc.setUserInfo(userInfo);
        log.info("sendCommand: {}", cc.getCommand());
        kafkaProducerService.sendMessage(cc);
    }
}
