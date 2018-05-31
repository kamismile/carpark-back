package ru.neoflex.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum States {

    READY ("READY", "В наличии"),
    IN_USE ("IN_USE", "В прокате"),
    IN_SERVICE ("IN_SERVICE", "На обслуживании"),
    DECOMMISSIONED ("DECOMMISSIONED", "Выбыл из автопарка");

    private String statusCode;
    private String description;
}
