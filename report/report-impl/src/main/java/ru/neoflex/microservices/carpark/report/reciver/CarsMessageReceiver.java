package ru.neoflex.microservices.carpark.report.reciver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;
import ru.neoflex.microservices.carpark.report.model.CarCommand;

import java.util.concurrent.CountDownLatch;

/**
 * @author rmorenko
 */
@Slf4j
@Configuration
public class CarsMessageReceiver {

        private CountDownLatch latch = new CountDownLatch(1);

        public CountDownLatch getLatch() {
                return latch;
        }

        @KafkaListener(topics = "car.t")
        public void receive(CarCommand command) {
                log.info("received command='{}'", command.toString());
                latch.countDown();
        }
}
