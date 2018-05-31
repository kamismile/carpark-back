package ru.neoflex.microservices.carpark.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@AllArgsConstructor
public class LifecycleServiceImpl implements LifecycleService {


    private StateMachinePoolService stateMachinePool;

    @Override
    public States doTransition(Car car, Events event) {

        StateMachine stateMachine = getAndStartMachine(car);
        boolean success = stateMachine.sendEvent(event.name());
        if (!success) {
            throw new TransitionUnsupportedException(event.name(), car.getState().name());
        }
        //TODO отсылаем сообщение в кафку
        State<String, String> newInnerState = stateMachine.getState();
        States result = States.valueOf(newInnerState.getId());
        stopAndReturnMachine(stateMachine);
        return result;
    }

    @Override
    public List<Events> getAvailableTransitions(Car car) {
        StateMachine stateMachine = getAndStartMachine(car);

        List<Events> result = new ArrayList<>();
        Collection<Transition<String, String>> a = stateMachine.getTransitions();
        for (Transition<String, String> t : a) {
            if (t.getSource().getId().equals(stateMachine.getState().getId())) {
                String action = t.getTrigger().getEvent();
                result.add(Events.valueOf(action));
            }
        }
        stopAndReturnMachine(stateMachine);
        return result;
    }


    private void stopAndReturnMachine(StateMachine stateMachine) {
        stateMachine.stop();
        stateMachinePool.giveBack(stateMachine);
    }

    private StateMachine getAndStartMachine(Car car) {
        StateMachine stateMachine;
        try {
            stateMachine = stateMachinePool.borrow();
            List<StateMachineAccess<String, String>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
            for (StateMachineAccess<String, String> a : withAllRegions) {
                a.resetStateMachine(new DefaultStateMachineContext<String, String>(car.getState().name(), null, null, null));
            }
            stateMachine.start();
        } catch (Exception ex) {
            throw new LifecycleException("Lifecycle service was unable to initialize state machine", ex);
        }
        return stateMachine;
    }
}
