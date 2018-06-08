/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.service;

import ru.vtb.microservices.carpark.cars.model.NextStatusEvent;

/**
 * Интерфейс отправки сообщений об следующем статусе.
 *
 * @author Denis_Begun
 */
public interface KafkaProducerService {

    /**
     * Отправка сообщения
     * @param event сообщение
     */
    void sendMessage(NextStatusEvent event);
}
