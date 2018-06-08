package ru.vtb.microservices.carpark.cars.listener;

import lombok.RequiredArgsConstructor;
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
import ru.vtb.microservices.carpark.cars.model.NextStatus;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumer {

    private final CarService carService;

    @KafkaListener(id = "cars", topics = "${kafka.orders.topic}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, NextStatusEvent> cr, Acknowledgment acknowledgment) {
        NextStatus nextStatus = cr.value().getEntity();
        Long carId = nextStatus.getCarId();
        Car car = carService.getCar(carId);
        car.setNextStatusDate(nextStatus.getNextStatusDate());
        car.setNextStatus(nextStatus.getNextStatus());
        if (PreorderType.SERVICE == nextStatus.getType()
                && (car.getNextMaintenanceDate() == null
                || car.getNextMaintenanceDate().getTime() > nextStatus.getNextStatusDate().getTime() )) {
            car.setNextMaintenanceDate(nextStatus.getNextStatusDate());
        }
        carService.updateCar(cr.value().getUserInfo(), car);
        acknowledgment.acknowledge();
    }
}
