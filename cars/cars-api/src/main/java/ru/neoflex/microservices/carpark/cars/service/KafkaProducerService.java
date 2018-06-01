package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;

public interface KafkaProducerService {

    void sendMessage(CarCommand carCommand);

}
