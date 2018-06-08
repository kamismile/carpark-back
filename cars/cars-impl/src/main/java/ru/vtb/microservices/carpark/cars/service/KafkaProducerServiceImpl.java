/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.cars.model.CarCommand;

/**
 * Имплементация сервиса для отсылки сообшений об изменении автомобиля.
 *
 * @author Denis_Begun
 */
@Service
@EnableKafka
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, CarCommand> kafkaTemplate;

    @Value("${kafka.cars.topic}")
    private String topic;

    @Override
    public void sendMessage(CarCommand carCommand) {
        kafkaTemplate.send(topic, "carCommand", carCommand);
    }

}
