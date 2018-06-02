package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.neoflex.microservices.carpark.cars.model.CarEvent;

public interface KafkaConsumerService {

    void listen(ConsumerRecord<String, CarEvent> cr) throws Exception;

}
