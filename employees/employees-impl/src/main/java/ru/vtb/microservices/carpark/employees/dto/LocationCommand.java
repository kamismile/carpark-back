/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.dto;

import ru.vtb.microservices.carpark.commons.model.KafkaCommand;
import ru.vtb.microservices.carpark.employees.model.Location;

/**
 * Wraper for Location kafka message.
 *
 * @author Roman_Morenko
 */
public class LocationCommand extends KafkaCommand<Location> {
}
