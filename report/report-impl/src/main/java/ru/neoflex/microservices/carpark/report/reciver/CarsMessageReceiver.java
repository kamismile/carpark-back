package ru.neoflex.microservices.carpark.report.reciver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;
import ru.neoflex.microservices.carpark.dicts.feign.DictsFeign;
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.model.CarEvent;
import ru.neoflex.microservices.carpark.report.service.CarEventResourceService;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @author rmorenko
 */
@Slf4j
@Configuration
@Data
public class CarsMessageReceiver {

        private CountDownLatch latch = new CountDownLatch(1);

        public CountDownLatch getLatch() {
                return latch;
        }

        @Autowired
        private CarEventResourceService carEventResourceService;

        @Autowired
        private DictsFeign dictsFeign;


        @KafkaListener(topics = "${kafka.topic.json}")
        public void receive(CarCommand command) {
                log.info("received command='{}'", command.toString());
                latch.countDown();
                Car car = command.getEntity();
                CarEvent carEvent = new CarEvent();
                carEvent.setMessageDate(command.getMessageDate());
                carEvent.setFio(command.getUserInfo().getName());
                carEvent.setUserName(command.getUserInfo().getName());
                BeanUtils.copyProperties(car, carEvent);
                String statusText = dictsFeign.getReferencesByRubric("car_status").stream().filter(v -> v.getCode().equals(car.getCurrentStatus().toLowerCase())).collect(
                        Collectors.toList()).get(0).getTitle();
                carEvent.setCurentStatusDesc(statusText);
                carEventResourceService.add(carEvent);
        }
}
