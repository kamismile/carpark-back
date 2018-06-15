/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.NextStatus;
import ru.vtb.microservices.carpark.cars.model.NextStatusEvent;
import ru.vtb.microservices.carpark.cars.model.PreorderType;
import ru.vtb.microservices.carpark.cars.service.CarService;

/**
 * Потребитель сообщений об изменении следующего статуса автомобиля.
 *
 * @author Denis_Begun
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumer {

    private final CarService carService;

    @KafkaListener(id = "cars", topics = "${kafka.orders.topic}")
    public void listen(NextStatusEvent nse) {
        log.info("received command='{}'", nse);
        NextStatus nextStatus = nse.getEntity();
        Long carId = nextStatus.getCarId();
        Car car = carService.getCar(carId);
        log.info("received command='{}'", nse);
        car.setNextStatusDate(nextStatus.getNextStatusDate());
        car.setNextStatus(nextStatus.getNextStatus());
        carService.updateCar(nse.getUserInfo(), car);
    }
}
