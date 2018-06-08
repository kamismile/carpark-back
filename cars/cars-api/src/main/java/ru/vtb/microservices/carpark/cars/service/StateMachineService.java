/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import ru.vtb.microservices.carpark.cars.model.Transition;

import java.util.List;

/**
 * Сервис, для настройки жизненного цикла автомобилей.
 *
 * @author Denis_Begun
 */
public interface StateMachineService {

    /**
     * Получение списка возможных переходов.
     *
     * @return список переходов
     */
    List<Transition> getTransitions();

    /**
     * Добавление перехода.
     *
     * @param transition добавляемый переход
     * @return добавленный переход
     */
    Transition addTransition(Transition transition);

    /**
     * Обновление перехода.
     *
     * @param transition обновляемый переход
     * @return обновленный переход
     */
    Transition updateTransition(Transition transition);

    /**
     * Удаление перехода.
     *
     * @param id идентификатор перехода
     */
    void deleteTransition(Long id);
}
