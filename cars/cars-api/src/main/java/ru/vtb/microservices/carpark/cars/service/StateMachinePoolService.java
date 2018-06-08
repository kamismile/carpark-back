/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import org.springframework.statemachine.StateMachine;

/**
 * Сервис, реализацющий пул конечных автоматов для сервиса жизненного цикла.
 *
 * @author Denis_Begun
 */
public interface StateMachinePoolService {
    /**
     * Получает из пула экземпляр стейт-машины
     *
     * @return Экземпляр стейт-машины.
     */
    StateMachine<String, String> borrow();

    /**
     * Возвращает экземпляр стейт-машины в пул
     *
     * @param machine Возвращаемая в пул стейт-машина.
     */
    void giveBack(StateMachine<String, String> machine);
}
