package ru.neoflex.microservices.carpark.dict.report.reciver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.neoflex.microservices.carpark.dict.report.model.ReferenceCommand;
import ru.neoflex.microservices.carpark.dict.report.service.ReferenceService;

import java.util.concurrent.CountDownLatch;

/**
 * @author vanosov
 */
@Slf4j
@Configuration
@Data
public class Receiver {

        private CountDownLatch latch = new CountDownLatch(1);

        public CountDownLatch getLatch() {
                return latch;
        }

        @Autowired
        ReferenceService referenceService;

        @KafkaListener(topics = "${kafka.topic.reference}")
        public void receiveReference(ReferenceCommand referenceCommand) {
                log.info("received command='{}'", referenceCommand.toString());
                latch.countDown();
                referenceService.save(referenceCommand);
        }

}
