/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import lombok.Data;

import java.util.List;

/**
 * Filter for car.
 *
 * @author Roman_Morenko
 */
@Data
public class CarFilter {

    private List<String> marks;

    private Integer yearFrom;

    private Integer yearTo;

    private Long currentLocationId;

    private Double mileageFrom;

    private Double mileageTo;

    private Long locationId;

    private List<String> currentStatuses;

}
