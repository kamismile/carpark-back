/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.service;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.report.model.CarCommand;
import ru.vtb.microservices.carpark.report.model.CarEvent;
import ru.vtb.microservices.carpark.report.repository.CarEventRepository;
import ru.vtb.microservices.carpark.report.repository.EmployeeRepository;


/**
 * Controller for car event.
 *
 * @author Roman_Morenko
 */
@Service
@Data
public class CarEventResourceService {

    private static Logger LOGGER = LoggerFactory.getLogger(CarEventResourceService.class);

    @Autowired
    private CarEventRepository carEventRepository;

    @Autowired
    private EmployeeRepository employeeRepository;



   /**
   *  Command for save.
    *
   * @param carCommand comand
   */
    public void save(CarCommand carCommand) {
        CarEvent carEvent = new CarEvent();
        carEvent.setMessageDate(carCommand.getMessageDate());
        carEvent.setUserName(carCommand.getUserInfo().getName());
        try {
            Employee employee = employeeRepository.findByUserLogin(carCommand.getUserInfo().getName());
            carEvent.setEmployee(employee);
        } catch ( DataAccessException ex ) {
            LOGGER.info(ex.getMessage(), ex);
            carEvent.setEmployee(null);
        }
        Car car = carCommand.getEntity();
        BeanUtils.copyProperties(car,carEvent);
        carEvent.setId(null);
        carEvent.setCarId(car.getId());
        carEvent.setMessageType(carCommand.getCommand().toString());
        carEventRepository.save(carEvent);
    }

}
