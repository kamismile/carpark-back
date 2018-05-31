package ru.neoflex.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import ru.neoflex.microservices.carpark.cars.exception.LifecycleException;

@AllArgsConstructor
public enum Events {

    RENT ("Выдать"),
    SERVICE ("В сервис"),
    RETURN ("Вернуть"),
    RETIRE ("Вывести из автопарка");

    private String description;

    public static Events fromString (String string) {
        Events result;
        try {
            result = Events.valueOf(string.toUpperCase());
        } catch (Exception ex) {
            throw new LifecycleException("Event " + string + "not present in current lifecycle model", ex);
        }
        return result;
    }
}
