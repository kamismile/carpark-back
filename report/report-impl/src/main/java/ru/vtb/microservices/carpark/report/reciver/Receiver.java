/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.reciver;
/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.vtb.microservices.carpark.report.service.CarEventResourceService;
import ru.vtb.microservices.carpark.report.service.EmployeeService;
import ru.vtb.microservices.carpark.report.service.LocationService;
import ru.vtb.microservices.carpark.report.model.CarCommand;
import ru.vtb.microservices.carpark.report.model.EmployeeCommand;
import ru.vtb.microservices.carpark.report.model.LocationCommand;


/**
 * Kafka reciver.
 *
 * @author Roman_Morenko
 */
@Slf4j
@Configuration
@Data
public class Receiver {

    public static final String RECEIVED_COMMAND = "received command='{}'";

    @Autowired
    private CarEventResourceService carEventResourceService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private EmployeeService employeeService;


    @KafkaListener(id = "report", topics = "${kafka.topic.car}")
    public void receiveCar(CarCommand command) {
        log.info(RECEIVED_COMMAND, command.toString());
        carEventResourceService.save(command);
    }

    @KafkaListener(id = "report2", topics = "${kafka.topic.employee}")
    public void receiveEmployee(EmployeeCommand employeeCommand) {
        log.info(RECEIVED_COMMAND, employeeCommand.toString());
        employeeService.save(employeeCommand);

    }

    @KafkaListener(id = "report3", topics = "${kafka.topic.location}")
    public void receiveLocation(LocationCommand locationCommand) {
        log.info(RECEIVED_COMMAND, locationCommand.toString());
        locationService.save(locationCommand);
    }

}
