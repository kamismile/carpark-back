package ru.neoflex.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PreorderType {

    BOOKING("in_use"),
    SERVICE("in_service");

    @Getter
    private String nextStatus;
}
