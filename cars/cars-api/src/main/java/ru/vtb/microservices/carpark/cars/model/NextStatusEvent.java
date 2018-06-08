/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import ru.vtb.microservices.carpark.commons.model.KafkaCommand;

/**
 * Событие с информацией об изменении следующего статуса.
 *
 * @author Denis_Begun
 */
@AllArgsConstructor
public class NextStatusEvent extends KafkaCommand<NextStatus> {
}
