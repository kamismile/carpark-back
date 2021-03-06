/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum состояний автомобиля.
 *
 * @author Denis_Begun
 */
@AllArgsConstructor
@Getter
public enum States {

    READY("ready", "В наличии"),
    IN_USE("in_use", "В прокате"),
    IN_SERVICE("in_service", "На обслуживании"),
    DECOMMISSIONED("decommissioned", "Выбыл из автопарка");

    private String statusCode;
    private String description;
}
