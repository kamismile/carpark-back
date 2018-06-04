/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.exception.LifecycleException;
import ru.neoflex.microservices.carpark.cars.exception.TransitionUnsupportedException;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Сервис жизненного цикла.
 * @author Denis_Begun
 * @inheritDoc
 */

@Service
@AllArgsConstructor
public class LifecycleServiceImpl implements LifecycleService {


    private final StateMachinePoolService stateMachinePool;

    private final Map<States, List<Events>> availableActionsMap = new HashMap<>();

    @Override
    public States doTransition(Car car, Events event) {
        StateMachine stateMachine = getRunningStateMachineForCar(car);
        boolean success = stateMachine.sendEvent(event.name());
        if (!success) {
            throw new TransitionUnsupportedException(event.name(), car.getState().name());
        }

        State<String, String> newInnerState = stateMachine.getState();
        States result = States.valueOf(newInnerState.getId());
        stopAndReturnMachine(stateMachine);
        return result;
    }

    @Override
    public List<Events> getAvailableTransitions(Car car) {
        States currentState = car.getState();
        return availableActionsMap.get(currentState);
    }

    @PostConstruct
    private void initAvailableEvents() {
        Arrays.stream(States.values()).forEach(e -> {
                    StateMachine stateMachine = getRunningStateMachineWithState(e.name());
                    List<Events> result = new ArrayList<>();
                    Collection<Transition<String, String>> a = stateMachine.getTransitions();
                    for (Transition<String, String> t : a) {
                        if (t.getSource().getId().equals(stateMachine.getState().getId())) {
                            String action = t.getTrigger().getEvent();
                            result.add(Events.valueOf(action));
                        }
                    }
                    stopAndReturnMachine(stateMachine);
                    availableActionsMap.put(e, result);
                }
        );
    }

    private StateMachine getRunningStateMachineForCar(Car car) {
        String currentState = car.getState().name();
        return getRunningStateMachineWithState(currentState);
    }

    private StateMachine getRunningStateMachineWithState(String currentState) {
        StateMachine stateMachine;
        try {
            stateMachine = stateMachinePool.borrow();
            List<StateMachineAccess<String, String>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
            for (StateMachineAccess<String, String> a : withAllRegions) {
                a.resetStateMachine(new DefaultStateMachineContext<String, String>(currentState, null, null, null));
            }
            stateMachine.start();
        } catch (Exception ex) {
            throw new LifecycleException("Lifecycle service was unable to initialize state machine", ex);
        }
        return stateMachine;
    }


    private void stopAndReturnMachine(StateMachine stateMachine) {
        stateMachine.stop();
        stateMachinePool.giveBack(stateMachine);
    }
}