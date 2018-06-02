package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.CarEvent;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @KafkaListener(id = "cars", topics = "${kafka.cars.topic}", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void listen(ConsumerRecord<String, CarEvent> cr) throws Exception {
        System.out.println("---------------> we have got a message: " + cr.value());
    }

}
