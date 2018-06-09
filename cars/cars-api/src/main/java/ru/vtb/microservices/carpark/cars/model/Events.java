/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import ru.vtb.microservices.carpark.cars.exception.LifecycleException;

/**
 * Enum событий для перехода между состояниями автомобиля.
 *
 * @author Denis_Begun
 */
@AllArgsConstructor
@SuppressWarnings("all")
public enum Events {

    RENT("Выдать"),
    SERVICE("В сервис"),
    RETURN("Вернуть"),
    RETIRE("Вывести из автопарка");

    private String description;

    public static Events fromString(String string) {
        Events result;
        try {
            result = Events.valueOf(string.toUpperCase());
        } catch (Exception ex) {
            throw new LifecycleException("Event " + string + "not present in current lifecycle model", ex);
        }
        return result;
    }
}
