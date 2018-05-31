package ru.neoflex.microservices.carpark.cars.service;

import ru.neoflex.microservices.carpark.cars.exception.TransitionUnsupportedException;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;

import java.util.List;

public interface LifecycleService {

    /**
     * Выполняет перевод состояния стейт машины для переданной сущности автомобиля.
     *
     * @param car   сущность автомобиля
     * @param event событие перехода
     * @return новый статус
     * @throws TransitionUnsupportedException в случае, если не удалось выполнить
     *                                        переход в соответсвии со статусной моделью
     */
    States doTransition(Car car, Events event) throws TransitionUnsupportedException;

    /**
     * Возвращает список возможных переходов для сущности автомобиля.
     *
     * @param car сущность автомобиля
     * @return список возможных событий для перехода
     */
    List<Events> getAvailableTransitions(Car car);

}
