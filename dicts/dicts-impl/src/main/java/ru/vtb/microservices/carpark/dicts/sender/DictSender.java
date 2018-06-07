package ru.vtb.microservices.carpark.dicts.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author vanosov
 */
@Slf4j
@Configuration
public class DictSender {

    @Autowired
    private KafkaTemplate<String, ?> kafkaTemplate;

    public void send(String topic, Object payload) {
        log.info("sending payload='{}' to topic='{}'", payload.toString(), topic);
        kafkaTemplate
                .send(MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.TOPIC, topic).build());
    }
}
