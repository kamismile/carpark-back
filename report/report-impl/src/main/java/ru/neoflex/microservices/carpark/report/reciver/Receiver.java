package ru.neoflex.microservices.carpark.report.reciver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.neoflex.microservices.carpark.report.model.*;
import ru.neoflex.microservices.carpark.report.service.CarEventResourceService;
import ru.neoflex.microservices.carpark.report.service.EmployeeService;
import ru.neoflex.microservices.carpark.report.service.LocationService;



import java.util.concurrent.CountDownLatch;


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

        @Autowired
        private LocationService locationService;

        @Autowired
        private EmployeeService employeeService;


        @KafkaListener(topics = "${kafka.topic.car}")
        public void receiveCar(CarCommand command) {
                log.info("received command='{}'", command.toString());
                latch.countDown();
                carEventResourceService.save(command);
        }

        @KafkaListener(topics = "${kafka.topic.employee}")
        public void receiveEmployee(EmployeeCommand employeeCommand) {
                log.info("received command='{}'", employeeCommand.toString());
                latch.countDown();
                employeeService.save(employeeCommand);

        }

        @KafkaListener(topics = "${kafka.topic.location}")
        public void receiveLocation(LocationCommand locationCommand) {
                log.info("received command='{}'", locationCommand.toString());
                latch.countDown();
                locationService.save(locationCommand);
        }

}
