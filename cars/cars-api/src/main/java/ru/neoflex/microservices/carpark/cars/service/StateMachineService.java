package ru.neoflex.microservices.carpark.cars.service;

import ru.neoflex.microservices.carpark.cars.model.Transition;

import java.util.List;

public interface StateMachineService {

    List<Transition> getTransitions();

    Transition addTransition (Transition transition);

    Transition updateTransition (Transition transition);

    void deleteTransition (Long id);
}
