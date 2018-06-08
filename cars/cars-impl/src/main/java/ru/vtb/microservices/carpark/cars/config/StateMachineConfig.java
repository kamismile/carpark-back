/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.cars.service.StateMachineService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Настройка конфигуратора конечного автомата для сервиса жизненного цикла.
 *
 * @author Denis_Begun
 */
@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    private final StateMachineService stateMachineService;


    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
                .withStates()
                .initial(States.READY.name())
                .states(Arrays.stream(States.values())
                        .map(a -> a.name())
                        .collect(Collectors.toSet()));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        List<Transition> transitionFromRepository = stateMachineService.getTransitions();
        for (Transition tr : transitionFromRepository) {
            transitions
                    .withExternal()
                    .source(tr.getFrom().name()).target(tr.getTo().name())
                    .event(tr.getEvent().name())
                    .and();
        }
    }
}
