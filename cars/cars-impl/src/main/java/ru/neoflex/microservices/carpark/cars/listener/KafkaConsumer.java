package ru.neoflex.microservices.carpark.cars.listener;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.CarCommand;
import ru.neoflex.microservices.carpark.cars.service.CarService;
import ru.neoflex.microservices.carpark.cars.service.KafkaProducerService;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.preorders.model.NextStatus;
import ru.neoflex.microservices.carpark.preorders.model.NextStatusEvent;
import ru.neoflex.microservices.carpark.preorders.model.PreorderType;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumer {

    private final CarService carService;
    private final KafkaProducerService kafkaProducerService;

    @KafkaListener(id = "cars", topics = "${kafka.orders.topic}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, NextStatusEvent> cr, Acknowledgment acknowledgment) {
        NextStatus nextStatus = cr.value().getEntity();
        Long carId = nextStatus.getCarId();
        Car car = carService.getCar(carId);
        car.setNextStatusDate(nextStatus.getNextStatusDate());
        car.setNextStatus(nextStatus.getNextStatus());
        if (PreorderType.SERVICE.equals(nextStatus.getType())
                && (car.getNextMaintenanceDate() == null
                || car.getNextMaintenanceDate().getTime() > nextStatus.getNextStatusDate().getTime() )) {
            car.setNextMaintenanceDate(nextStatus.getNextStatusDate());
        }
        carService.updateCar(cr.value().getUserInfo(), car);

        CarCommand cc = new CarCommand();
        cc.setCommand(Command.UPDATE);
        cc.setEntity(car);
        cc.setMessageDate(cr.value().getMessageDate());
        cc.setUserInfo(cr.value().getUserInfo());
        kafkaProducerService.sendMessage(cc);

        acknowledgment.acknowledge();
    }
}
