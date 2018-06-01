package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;

public interface KafkaService {

    void listen(ConsumerRecord<String, CarCommand> cr) throws Exception;

    void sendMessage(CarCommand carCommand);

}
