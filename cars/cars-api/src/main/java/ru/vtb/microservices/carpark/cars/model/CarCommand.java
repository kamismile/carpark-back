/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import ru.vtb.microservices.carpark.commons.model.KafkaCommand;

/**
 * Событие об изменении состояния автомобиля.
 *
 * @author Denis_Begun
 */
public class CarCommand extends KafkaCommand<Car> {
}
