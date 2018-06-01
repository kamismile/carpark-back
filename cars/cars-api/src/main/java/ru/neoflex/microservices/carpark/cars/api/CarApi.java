/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.cars.api;

import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

import java.util.List;

/**
 * Интерфейс контроллера автомашин.
 * @author Denis_Begun
 */
public interface CarApi {

    List<Car> getCars(UserInfo userInfo);

    Car getCar(UserInfo userInfo, Long id);

    Car updateCar(UserInfo userInfo, Long id, Car car);

    Car createCar(UserInfo userInfo, Car car);

    void deleteCar(UserInfo userInfo, Long id);

    Car changeCarState(UserInfo userInfo, Long id, String stringEvent);
}
