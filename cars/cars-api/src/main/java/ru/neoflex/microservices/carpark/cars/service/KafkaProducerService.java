package ru.neoflex.microservices.carpark.cars.service;

import ru.neoflex.microservices.carpark.cars.model.CarEvent;

public interface KafkaProducerService {

    void sendMessage(CarEvent carEvent);

}
