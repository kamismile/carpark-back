/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.report.model.LocationCommand;
import ru.vtb.microservices.carpark.report.repository.LocationRepository;

import java.util.Date;

/**
 *  Location service.
 *
 * @author Roman_Morenko
 */
@Service
@Slf4j
@Transactional
public class LocationService {

    @Autowired
    LocationRepository repository;

    public void save(LocationCommand cmd) {
        if (Command.DELETE == cmd.getCommand()) {
            cmd.getEntity().setActive(false);
            repository.save(cmd.getEntity());
        } else if (Command.ADD == cmd.getCommand()) {
            cmd.getEntity().setId(null);
        } else {
            Location oldLocation =  repository.findByAddress(cmd.getOldEntity().getAddress());
            repository.delete(oldLocation);
            cmd.getEntity().setId(null);
        }
        cmd.setMessageDate(new Date());
        repository.save(cmd.getEntity());
    }

}
