package ru.neoflex.microservices.carpark.cars.service;

import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.CarFilter;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

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
