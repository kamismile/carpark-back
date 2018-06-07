package ru.neoflex.microservices.carpark.preorders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.NextStatusEvent;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, NextStatusEvent> kafkaTemplate;

    @Value("${kafka.orders.topic}")
    private String topic;

    @Override
    public void sendMessage(NextStatusEvent nsEvent) {
        kafkaTemplate.send(topic, nsEvent);
    }
}
