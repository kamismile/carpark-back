/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.model;

import lombok.Data;

import java.util.List;

/**
 * Filter for locations api.
 *
 * @author Roman_Morenko
 */
@Data
public class LocationFilter {

    private String address;

    private List<String> locationTypes;

    private Boolean active;


}
