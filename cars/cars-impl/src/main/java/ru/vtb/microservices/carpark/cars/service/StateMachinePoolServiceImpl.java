/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/**
 * Реализация сервиса пула объектов стейт-машны.
 *
 * @author Denis_Begun
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachinePoolServiceImpl implements StateMachinePoolService {

    private final BlockingQueue<StateMachine<String, String>> machines = new ArrayBlockingQueue<>(3, false);
    private final StateMachineFactory<String, String> factory;

    @PostConstruct
    public void init() {
        IntStream.range(0, 3).forEach(e -> {
            StateMachine<String, String> machine = factory.getStateMachine();
            boolean successful = machines.offer(machine);
            if (!successful) {
                log.warn("Could not add stateMachine to cache");
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
                log.warn("Could not return stateMachine to cache");
            }
        }
    }
}
