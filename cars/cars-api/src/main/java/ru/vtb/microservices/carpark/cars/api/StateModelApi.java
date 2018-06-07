package ru.vtb.microservices.carpark.cars.api;

import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;

import java.util.List;

public interface StateModelApi {

    List<Transition> getTransitions ();

    List<States> getPossibleStates ();

    List<Events> getPossibleEvents ();

    Transition addTransition(UserInfo userInfo, Transition transition);

    Transition updateTransition (UserInfo userInfo, Long id, Transition transition);

    void deleteTransition(UserInfo userInfo, Long id);
}
