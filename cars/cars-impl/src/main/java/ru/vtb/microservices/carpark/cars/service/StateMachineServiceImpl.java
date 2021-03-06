/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.cars.repository.StateMachineTransitionRepository;

import java.util.List;

/**
 * Имплементация сервиса для настройки жизненного цикла автомобиля.
 *
 * @author Denis_Begun
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachineServiceImpl implements StateMachineService {

    private final StateMachineTransitionRepository repository;

    @Override
    public List<Transition> getTransitions() {
        return repository.findAll();
    }

    @Override
    public Transition addTransition(Transition transition) {
        return repository.save(transition);
    }

    @Override
    public Transition updateTransition(Transition transition) {
        return repository.save(transition);
    }

    @Override
    public void deleteTransition(Long id) {
        repository.delete(id);
    }
}
