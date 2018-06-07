package ru.neoflex.microservices.carpark.cars.service;


import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.neoflex.microservices.carpark.cars.repository.CarSpecifications.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;
import ru.neoflex.microservices.carpark.cars.model.CarFilter;
import ru.neoflex.microservices.carpark.cars.repository.CarRepository;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Car service realization
 *
 * @autor dbegun
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
        log.info(" sendCommand " + cc.getCommand());
        kafkaProducerService.sendMessage(cc);
    }
}
