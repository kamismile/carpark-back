package ru.neoflex.microservices.carpark.cars.service;

import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.CarFilter;

import java.util.List;

public interface CarService {

    List<Car> getAllCars(CarFilter filter);

    Car getCar(Long id);

    Car createCar(Car car);

    Car updateCar(Car car);

    void deleteById (Long id);

}
