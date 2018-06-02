package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import ru.neoflex.microservices.carpark.cars.model.CarEvent;
import ru.neoflex.microservices.carpark.preorders.model.NextStatusEvent;

public interface KafkaConsumerService {

    void listen(ConsumerRecord<String, NextStatusEvent> cr) throws Exception;

}
