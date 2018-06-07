package ru.vtb.microservices.carpark.cars.service;

import org.springframework.statemachine.StateMachine;

public interface StateMachinePoolService {
    /**
     * Получает из пула экземпляр стейт-машины
     * @return
     */
    StateMachine<String,String> borrow();

    /**
     * Возвращает экземпляр стейт-машины в пул
     * @param machine
     */
    void giveBack(StateMachine<String,String> machine);
}
