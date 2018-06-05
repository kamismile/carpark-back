package ru.neoflex.microservices.carpark.cars.api;

import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.model.Transition;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

import java.util.List;

public interface StateModelApi {

    List<Transition> getTransitions ();

    List<States> getPossibleStates ();

    List<Events> getPossibleEvents ();

    Transition addTransition(UserInfo userInfo, Transition transition);

    Transition updateTransition (UserInfo userInfo, Long id, Transition transition);

    void deleteTransition(UserInfo userInfo, Long id);
}
