package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;

@FunctionalInterface
public interface KafkaConsumerService {

    void listen(ConsumerRecord<String, CarCommand> cr) throws Exception;

}
