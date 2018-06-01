package ru.neoflex.microservices.carpark.report.reciver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;
import ru.neoflex.microservices.carpark.dicts.feign.DictsFeign;
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
                carEvent.setUserName(command.getUserInfo().getName());
                BeanUtils.copyProperties(car, carEvent);
                CompletableFuture<String> statusText = getCarStatus(car);
                CompletableFuture<String> statusTextNext = getNextCarStatus(car);
                CompletableFuture<String> markDesc = getCarMark(car);
                CompletableFuture.allOf(statusText, statusTextNext, markDesc).join();
                try {
                        carEvent.setCurentStatusDesc(statusText.get());
                        carEvent.setNextStatusDesc(statusTextNext.get());
                        carEvent.setMarkDesc(markDesc.get());
                } catch (InterruptedException | ExecutionException ex){
                   log.trace("Get titles error", ex);
                }
                carEventResourceService.add(carEvent);
        }

        @Async
        private CompletableFuture<String> getCarMark(Car car) {
                return CompletableFuture.completedFuture(dictsFeign.getReferencesByRubric("car_mark").stream()
                        .filter(v -> v.getCode().equals(car.getMark().toLowerCase())).collect(
                        Collectors.toList()).get(0).getTitle());
        }
        @Async
        private CompletableFuture<String> getNextCarStatus(Car car) {
                return CompletableFuture.completedFuture(dictsFeign.getReferencesByRubric("car_status")
                        .stream().filter(v -> v.getCode().equals(car.getNextStatus().toLowerCase())).collect(
                        Collectors.toList()).get(0).getTitle());
        }
        @Async
        private CompletableFuture<String> getCarStatus(Car car) {
                return CompletableFuture.completedFuture(dictsFeign.getReferencesByRubric("car_status")
                        .stream().filter(v -> v.getCode().equals(car.getCurrentStatus().toLowerCase())).collect(
                        Collectors.toList()).get(0).getTitle());
        }


}
