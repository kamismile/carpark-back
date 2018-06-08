package ru.vtb.microservices.carpark.cars.service;

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

    Car getCar(Long id);

    Car createCar(UserInfo userInfo, Car car);

    Car updateCar(UserInfo userInfo, Car car);

    void upploadCar(UserInfo userInfo, Car car);

    void deleteById(UserInfo userInfo, Long id);

}
