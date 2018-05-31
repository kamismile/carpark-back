package ru.neoflex.microservices.carpark.cars.api;

import ru.neoflex.microservices.carpark.cars.model.Car;

import java.util.List;

public interface CarApi {

    List<Car> getCars();

    Car getCar(Long id);

    Car updateCar(Long id, Car car);

    Car createCar(Car car);

    void deleteCar(Long id);

    Car changeCarState(Long id, String stringEvent);
}
