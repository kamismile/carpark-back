package ru.vtb.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.vtb.microservices.carpark.cars.model.CarCommand;
import ru.vtb.microservices.carpark.cars.model.CarCommand;

public interface KafkaProducerService {

    void sendMessage(CarCommand carCommand);

}
