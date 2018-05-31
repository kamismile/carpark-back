package ru.neoflex.microservices.carpark.report.resource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.reciver.CarsMessageReceiver;
import ru.neoflex.microservices.carpark.report.reciver.Sender;

import java.util.Date;

/**
 * @author rmorenko
 */
@RestController
@AllArgsConstructor
@Slf4j
public class SendController {

        @Autowired
        private Sender sender;

        @Autowired
        private CarsMessageReceiver receiver;

        @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
        public void test() {
                Car car = new Car();
                car.setId(1L);
                car.setMark("Рыдван");
                car.setCurrentStatusDate(new Date());
                CarCommand command = new CarCommand();
                command.setCommand(Command.ADD);
                sender.send(command);


        }

}
