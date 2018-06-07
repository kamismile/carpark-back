package ru.neoflex.microservices.carpark.preorders.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.NextStatus;
import ru.neoflex.microservices.carpark.cars.model.NextStatusEvent;
import ru.neoflex.microservices.carpark.cars.model.PreorderType;
import ru.neoflex.microservices.carpark.cars.service.CarService;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumer {

    @KafkaListener(id = "preorders", topics = "${kafka.orders.topic}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, NextStatusEvent> cr, Acknowledgment acknowledgment) {
        log.info("received command='{}'", cr.value());
        acknowledgment.acknowledge();
    }
}
