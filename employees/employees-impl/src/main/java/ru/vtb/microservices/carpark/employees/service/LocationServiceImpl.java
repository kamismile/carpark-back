/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.service;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.vtb.microservices.carpark.employees.repository.LocationSpecifications.employeeInLocationTypes;
import static ru.vtb.microservices.carpark.employees.repository.LocationSpecifications.locationIsActive;
import static ru.vtb.microservices.carpark.employees.repository.LocationSpecifications.locationLikeAddresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.employees.dto.LocationCommand;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;
import ru.vtb.microservices.carpark.employees.repository.LocationRepository;
import ru.vtb.microservices.carpark.employees.sender.Sender;
import ru.vtb.microservices.carpark.employees.repository.LocationRepository;

import java.util.List;

/**
 * Service for location.
 *
 * @author Mirzoev_Nikita
 */
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final Sender sender;

    @Value("${kafka.topic.location}")
    String locationTopic;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, Sender sender) {
        this.locationRepository = locationRepository;
        this.sender = sender;
    }

    public Location getById(Long id) {
        return locationRepository.getOne(id);
    }

    @Override
    public void deactivate(Long locationId) {
        Location location = locationRepository.findOne(locationId);
        location.setActive(false);
        locationRepository.save(location);
        LocationCommand locationCommand = new LocationCommand();
        locationCommand.setCommand(Command.DELETE);
        locationCommand.setEntity(location);
        sender.send(locationTopic, locationCommand);
    }

    @Override
    public Location add(Location location) {
        location = locationRepository.save(location);
        LocationCommand locationCommand = new LocationCommand();
        locationCommand.setCommand(Command.ADD);
        locationCommand.setEntity(location);
        sender.send(locationTopic, locationCommand);
        return location;
    }

    @Override
    public void update(Location location) {
        Location oldLocation = locationRepository.findOne(location.getId());
        locationRepository.save(location);
        LocationCommand locationCommand = new LocationCommand();
        locationCommand.setOldEntity(oldLocation);
        locationCommand.setCommand(Command.UPDATE);
        locationCommand.setEntity(location);
        sender.send(locationTopic, locationCommand);
    }

    @Override
    public List<Location> getAll(LocationFilter filter) {
        return locationRepository.findAll(where(locationLikeAddresses(filter))
                .and(locationIsActive(filter))
                .and(employeeInLocationTypes(filter)));
    }
}
