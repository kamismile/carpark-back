package ru.vtb.microservices.carpark.dict.report.reciver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.vtb.microservices.carpark.dict.report.model.ReferenceCommand;
import ru.vtb.microservices.carpark.dict.report.service.ReferenceService;

/**
 * @author vanosov
 */
@Slf4j
@Configuration
@Data
public class Receiver {

    @Autowired
    ReferenceService referenceService;

    @KafkaListener(topics = "${kafka.topic.reference}")
    public void receiveReference(ReferenceCommand referenceCommand) {
        log.info("received command='{}'", referenceCommand.toString());
        referenceService.save(referenceCommand);
    }
}
