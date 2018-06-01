package ru.neoflex.microservices.carpark.report.reciver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import ru.neoflex.microservices.carpark.cars.model.Car;

import ru.neoflex.microservices.carpark.report.model.CarCommand;

/**
 * @author rmorenko
 */
@Slf4j
@Configuration
public class Sender {

        @Value("${kafka.topic.json}")
        private String jsonTopic;

        @Autowired
        private KafkaTemplate<String, CarCommand> kafkaTemplate;

        public void send(CarCommand command) {
              kafkaTemplate.send(jsonTopic, command);
        }
}
