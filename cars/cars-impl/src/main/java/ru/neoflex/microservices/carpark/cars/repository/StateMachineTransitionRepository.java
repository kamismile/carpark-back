package ru.neoflex.microservices.carpark.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.microservices.carpark.cars.model.Transition;

public interface StateMachineTransitionRepository extends JpaRepository<Transition, Long> {
}


