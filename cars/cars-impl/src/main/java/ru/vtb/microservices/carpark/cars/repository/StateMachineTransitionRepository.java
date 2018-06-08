package ru.vtb.microservices.carpark.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.cars.model.Transition;

public interface StateMachineTransitionRepository extends JpaRepository<Transition, Long> {
}


