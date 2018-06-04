package ru.neoflex.microservices.carpark.report.reciver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import ru.neoflex.microservices.carpark.cars.model.Car;

import ru.neoflex.microservices.carpark.report.model.CarCommand;

/**
 * @author rmorenko
 */
@Slf4j
@Configuration
public class Sender {

        @Autowired
        private KafkaTemplate<String, ?> kafkaTemplate;

        public void send(String topic, Object payload) {
                log.info("sending payload='{}' to topic='{}'", payload.toString(), topic);
                kafkaTemplate
                        .send(MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.TOPIC, topic).build());
        }
}
