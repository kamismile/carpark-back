/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dict.report.model;

import ru.vtb.microservices.carpark.commons.model.KafkaCommand;
import ru.vtb.microservices.carpark.employees.model.Location;

/**
 * Commoand for location.
 *
 * @author Roman_Morenko
 */
public class LocationCommand extends KafkaCommand<Location> {
}
