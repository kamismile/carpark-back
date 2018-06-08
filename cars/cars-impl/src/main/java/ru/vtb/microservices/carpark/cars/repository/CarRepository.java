package ru.vtb.microservices.carpark.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.cars.model.Car;

/**
 * @author mirzoevnik
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor {
}
