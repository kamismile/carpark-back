/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.model;

import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.commons.model.KafkaCommand;

/**
 * Command for Car.
 *
 * @author Roman_Morenko
 */
public class CarCommand extends KafkaCommand<Car> {
}
