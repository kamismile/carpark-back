/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.microservices.carpark.cars.api.StateModelApi;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.cars.service.LifecycleService;
import ru.vtb.microservices.carpark.cars.service.StateMachineService;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.cars.api.StateModelApi;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;

import java.util.Arrays;
import java.util.List;

/**
 * Реализация контроллера редактирования модели состояний.
 *
 * @author Denis_Begun
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateModelController implements StateModelApi {

    private final StateMachineService stateMachineService;

    /**
     * Получение списка возможных переходов.
     *
     * @return список переходов.
     */
    @Override
    public List<Transition> getTransitions() {
        return stateMachineService.getTransitions();
    }

    /**
     * Получение списка состояний.
     *
     * @return список состояний.
     */
    @Override
    public List<States> getPossibleStates() {
        return Arrays.asList(States.values());
    }

    /**
     * Получение списка событий для перехода
     *
     * @return список событий для перехода.
     */
    @Override
    public List<Events> getPossibleEvents() {
        return Arrays.asList(Events.values());
    }

    /**
     * Добавление возможного перехода.
     *
     * @param userInfo   Информация о пользователе (из токена).
     * @param transition Добавляемый переход.
     * @return Добавленный переход.
     */
    @Override
    public Transition addTransition(UserInfo userInfo, @RequestBody Transition transition) {
        return stateMachineService.addTransition(transition);
    }

    /**
     * Редактирование перехода.
     *
     * @param userInfo   Информация о пользователе (из токена).
     * @param id         Идентификатор перехода.
     * @param transition Обновляемый переход.
     * @return Обновленный переход.
     */
    @Override
    public Transition updateTransition(UserInfo userInfo, @PathVariable Long id, @RequestBody Transition transition) {
        transition.setId(id);
        return stateMachineService.updateTransition(transition);
    }

    /**
     * Удаление возможного перехода
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Идентификатор перехода.
     */
    @Override
    public void deleteTransition(UserInfo userInfo, @PathVariable Long id) {
        stateMachineService.deleteTransition(id);
    }
}
