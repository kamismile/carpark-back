package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.cars.model.NextStatusEvent;

public interface KafkaProducerService {

    void sendMessage(NextStatusEvent carCommand);
}
