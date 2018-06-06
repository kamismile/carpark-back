package ru.neoflex.microservices.carpark.cars.service;


import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.neoflex.microservices.carpark.cars.repository.CarSpecifications.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.CarFilter;
import ru.neoflex.microservices.carpark.cars.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

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
    public Car getCar(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.delete(id);
    }
}
