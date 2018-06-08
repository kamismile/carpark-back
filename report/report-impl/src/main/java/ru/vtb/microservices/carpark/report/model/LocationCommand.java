/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.model;

import ru.vtb.microservices.carpark.commons.model.KafkaCommand;
import ru.vtb.microservices.carpark.employees.model.Location;

/**
 * Command for location.
 *
 * @author Roman_Morenko
 */
public class LocationCommand extends KafkaCommand<Location> {
}
