/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import ru.vtb.microservices.carpark.cars.model.CarCommand;

/**
 * Сервис для отправки обновлений о сотоянии автомобиля.
 *
 * @author Denis_Begun
 */
public interface KafkaProducerService {

    void sendMessage(CarCommand carCommand);

}
