package ru.neoflex.microservices.carpark.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;

@Service
@EnableKafka
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, CarCommand> kafkaTemplate;

    @Override
    public void sendMessage(CarCommand carCommand) {
        kafkaTemplate.send("bookings2", "carCommand", carCommand);
    }

}
