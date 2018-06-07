package ru.neoflex.microservices.carpark.preorders.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.neoflex.microservices.carpark.cars.model.*;
import ru.neoflex.microservices.carpark.cars.service.CarService;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.service.PreorderService;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumer {

    private final PreorderService preorderService;

    private final JavaMailSender emailSender;

    @KafkaListener(id = "preorders", topics = "${kafka.cars.topic}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, CarCommand> cr, Acknowledgment acknowledgment) {
        log.info("received command='{}'", cr.value());

        Car car = cr.value().getEntity();
        if (States.READY == car.getState()) {
            Preorder preorder = preorderService.getEarliestPreorderByType(car.getId(), PreorderType.BOOKING);
            if (preorder != null) {
                if (car.getCurrentLocationId() != null
                        && car.getCurrentLocationId().equals(preorder.getStartLocationId())) {
                    log.info("Sending email to: " + preorder.getEmail());
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(preorder.getEmail());
                    message.setSubject("Автомобиль ожидает Вас в пункте проката");
                    message.setText
                            (String.format("%s %s, здравствуйте! Выбранный Вами автомобиль ожидает в пункте проката.",
                                    preorder.getClientName(), preorder.getClientPatronymic()));
                    emailSender.send(message);
                }
            }
        }

        acknowledgment.acknowledge();
    }
}
