/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.api;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;

import java.util.List;

/**
 * API контроллера редактирования модели состояний.
 *
 * @author Denis_Begun
 */
public interface StateModelApi {
    /**
     * Получение списка возможных переходов.
     *
     * @return список переходов.
     */
    @GetMapping(value = "/sm/transitions/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Transition> getTransitions();

    /**
     * Получение списка состояний.
     *
     * @return список состояний.
     */
    @GetMapping(value = "/sm/states/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<States> getPossibleStates();

    /**
     * Получение списка событий для перехода.
     *
     * @return список событий для перехода.
     */
    @GetMapping(value = "/sm/events/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Events> getPossibleEvents();

    /**
     * Добавление возможного перехода.
     *
     * @param userInfo   Информация о пользователе (из токена).
     * @param transition Добавляемый переход.
     * @return Добавленный переход.
     */
    @PreAuthorize("hasAuthority('administrator')")
    @PostMapping(value = "/sm/transitions/", produces = MediaType.APPLICATION_JSON_VALUE)
    Transition addTransition(UserInfo userInfo, Transition transition);

    /**
     * Редактирование перехода.
     *
     * @param userInfo   Информация о пользователе (из токена).
     * @param id         Идентификатор перехода.
     * @param transition Обновляемый переход.
     * @return Обновленный переход.
     */
    @PreAuthorize("hasAuthority('administrator')")
    @PutMapping(value = "/sm/transitions/{id}")
    Transition updateTransition(UserInfo userInfo, Long id, Transition transition);

    /**
     * Удаление возможного перехода.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Идентификатор перехода.
     */
    @PreAuthorize("hasAuthority('administrator')")
    @DeleteMapping(value = "/sm/transitions/{id}")
    void deleteTransition(UserInfo userInfo, Long id);
}
