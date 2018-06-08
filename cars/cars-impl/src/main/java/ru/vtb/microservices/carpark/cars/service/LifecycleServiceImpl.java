/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineException;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.cars.exception.LifecycleException;
import ru.vtb.microservices.carpark.cars.exception.TransitionUnsupportedException;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис жизненного цикла.
 *
 * @author Denis_Begun
 * @inheritDoc
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LifecycleServiceImpl implements LifecycleService {

    private final StateMachinePoolService stateMachinePool;
    private final Map<States, List<Events>> availableActionsMap = new EnumMap<>(States.class);

    @Override
    public States doTransition(Car car, Events event) {
        StateMachine<String, String> stateMachine = getRunningStateMachineForCar(car);
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
                    StateMachine<String, String> stateMachine = getRunningStateMachineWithState(e.name());
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

    private StateMachine<String, String> getRunningStateMachineForCar(Car car) {
        String currentState = car.getState().name();
        return getRunningStateMachineWithState(currentState);
    }

    private StateMachine<String, String> getRunningStateMachineWithState(String currentState) {
        StateMachine<String, String> stateMachine;
        try {
            stateMachine = stateMachinePool.borrow();
            List<StateMachineAccess<String, String>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
            for (StateMachineAccess<String, String> a : withAllRegions) {
                a.resetStateMachine(new DefaultStateMachineContext<String, String>(currentState, null, null, null));
            }
            stateMachine.start();
        } catch (RuntimeException ex) {
            throw new LifecycleException("Lifecycle service was unable to initialize state machine", ex);
        }
        return stateMachine;
    }


    private void stopAndReturnMachine(StateMachine<String, String> stateMachine) {
        stateMachine.stop();
        stateMachinePool.giveBack(stateMachine);
    }
}