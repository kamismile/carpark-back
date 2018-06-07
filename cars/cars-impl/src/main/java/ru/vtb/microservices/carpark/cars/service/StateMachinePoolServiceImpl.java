package ru.vtb.microservices.carpark.cars.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(StateMachinePoolServiceImpl.class);

    @PostConstruct
    public void init() {
        IntStream.range(0, maxPollSize).forEach(e -> {
            StateMachine<String, String> machine = factory.getStateMachine();
            boolean successful = machines.offer(machine);
            if (!successful) {
                log.warn("Could not add statemachine to cache");
            }
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
            boolean successful = machines.offer(machine);
            if (!successful) {
                log.warn("Could not return statemachine to cache");
            }
        }
    }
}
