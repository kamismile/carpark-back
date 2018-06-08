package ru.neoflex.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum States {

    READY ("ready", "В наличии"),
    IN_USE ("in_use", "В прокате"),
    IN_SERVICE ("in_service", "На обслуживании"),
    DECOMMISSIONED ("decommissioned", "Выбыл из автопарка");

    private String statusCode;
    private String description;
}
