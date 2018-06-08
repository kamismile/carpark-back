/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarFilter;

import java.util.List;

/**
 * Сервис работы с сущностью автомобиля.
 *
 * @author Denis_Begun
 */
public interface CarService {

    List<Car> getAllCars(CarFilter filter);

    Page<Car> getAllCars(UserInfo userInfo, CarFilter filter, PageRequest pageRequest);

    Car getCar(Long id);

    Car createCar(UserInfo userInfo, Car car);

    Car updateCar(UserInfo userInfo, Car car);

    void upploadCar(UserInfo userInfo, Car car);

    void deleteById(UserInfo userInfo, Long id);

}
