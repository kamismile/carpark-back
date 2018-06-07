package ru.neoflex.microservices.carpark.cars;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.microservices.carpark.cars.api.CarApi;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.model.CarFilter;
import ru.neoflex.microservices.carpark.cars.service.CarService;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

import java.util.Date;
import java.util.List;

/**
 * Controller for cars.
 *
 * @author Denis_Begun
 */

@AllArgsConstructor
@Slf4j
@RestController
public class CarController implements CarApi {

    private CarService carService;
    private LifecycleService lifecycleService;


    @Override
    public List<Car> getCars(UserInfo userInfo, CarFilter carFilter) {
        List<Car> list = carService.getAllCars(carFilter);
        list.forEach(car -> car.setAvailableEvents(lifecycleService.getAvailableTransitions(car)));
        return list;
    }

    @Override
    public Car getCar(UserInfo userInfo, @PathVariable(name = "id") Long id) {
        Car car = carService.getCar(id);
        car.setAvailableEvents(lifecycleService.getAvailableTransitions(car));
        return carService.getCar(id);
    }

    @Override
    public Car updateCar(UserInfo userInfo, @PathVariable(name = "id") Long id, @RequestBody Car car) {
        car.setId(id);
        return carService.updateCar(userInfo, car);
    }

    @Override
    public Car createCar(UserInfo userInfo, @RequestBody Car car) {
        return carService.createCar(userInfo, car);
    }

    @Override
    public void deleteCar(UserInfo userInfo, @PathVariable(name = "id") Long id) {
        carService.deleteById(userInfo, id);
    }

    @Override
    public Car changeCarState(UserInfo userInfo, @PathVariable(name = "id") Long id, @PathVariable(name = "stringEvent") String stringEvent) {
        Events event = Events.fromString(stringEvent);
        Car car = carService.getCar(id);
        States result = lifecycleService.doTransition(car, event);
        car.setState(result);
        car.setCurrentStatus(result.getStatusCode());
        car.setCurrentStatusDate(new Date());
        car = carService.updateCar(userInfo, car);
        return car;
    }

    @Override
    public List<Car> uploadAllCars(UserInfo userInfo) {
        List<Car> cars =  carService.getAllCars(new CarFilter());
        for (Car car : cars) {
            carService.upploadCar(userInfo,car);
        }
        return  cars;
    }

}
