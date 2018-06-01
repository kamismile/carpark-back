package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @KafkaListener(id = "cars", topics = "${kafka.cars.topic}", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void listen(ConsumerRecord<String, CarCommand> cr) throws Exception {
        System.out.println("---------------> we have got a message: " + cr.value());
    }

}
