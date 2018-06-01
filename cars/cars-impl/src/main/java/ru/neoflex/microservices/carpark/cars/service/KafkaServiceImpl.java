package ru.neoflex.microservices.carpark.cars.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;

@Service
@EnableKafka
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, CarCommand> kafkaTemplate;

    @KafkaListener(id = "cars", topics = "bookings2", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void listen(ConsumerRecord<String, CarCommand> cr) throws Exception {
        System.out.println("---------------> we have got a message: " + cr.value());
    }

    @Override
    public void sendMessage(CarCommand carCommand) {
        kafkaTemplate.send("bookings2", "carCommand", carCommand);
    }

}
