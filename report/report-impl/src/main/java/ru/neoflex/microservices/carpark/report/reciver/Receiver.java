package ru.neoflex.microservices.carpark.report.reciver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;
import ru.neoflex.microservices.carpark.dicts.feign.DictsFeign;
import ru.neoflex.microservices.carpark.employees.feign.EmployeeFeign;
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.model.CarEvent;
import ru.neoflex.microservices.carpark.report.service.CarEventResourceService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author rmorenko
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
        private CarEventResourceService carEventResourceService;

        @KafkaListener(topics = "${kafka.topic.json}") public void receive(CarCommand command) {
                log.info("received command='{}'", command.toString());
                latch.countDown();
                carEventResourceService.save(command);
        }



}
