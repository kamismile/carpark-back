package ru.neoflex.microservices.carpark.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachinePoolServiceImpl implements StateMachinePoolService {

    private final int maxPollSize = 3;
    private final BlockingQueue<StateMachine<String, String>> machines = new ArrayBlockingQueue<>(maxPollSize, false);

    private final StateMachineFactory<String, String> factory;

    @PostConstruct
    public void init() {
        IntStream.range(0, maxPollSize).forEach(e -> {
            StateMachine<String, String> machine = factory.getStateMachine();
            machines.offer(machine);
        });
    }

    @Override
    public StateMachine<String, String> borrow() {
        StateMachine<String, String> polled = machines.poll();
        if (polled != null) {
            return polled;
        } else {
            return factory.getStateMachine();
        }
    }

    @Override
    public void giveBack(StateMachine<String, String> machine) {
        if (machine != null && !machine.hasStateMachineError()) {
            machines.offer(machine);
        }
    }
}
