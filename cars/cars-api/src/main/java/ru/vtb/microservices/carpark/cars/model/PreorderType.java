/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Тип бронирования.
 *
 * @author Denis_Begun
 */
@AllArgsConstructor
public enum PreorderType {

    BOOKING("in_use"),
    SERVICE("in_service");

    @Getter
    private String nextStatus;
}
