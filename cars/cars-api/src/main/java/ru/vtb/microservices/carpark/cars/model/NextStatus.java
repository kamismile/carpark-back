/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Объект с информацией об изменении следующего статуса.
 *
 * @author Denis_Begun
 */
@AllArgsConstructor
@Data
public class NextStatus implements Serializable {
    private Long carId;
    private String nextStatus;
    private Date nextStatusDate;
    private PreorderType type;
}
