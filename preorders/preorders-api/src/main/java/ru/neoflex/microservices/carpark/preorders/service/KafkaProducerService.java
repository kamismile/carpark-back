package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.preorders.model.NextStatusEvent;

public interface KafkaProducerService {

    void sendMessage(NextStatusEvent carCommand);
}
