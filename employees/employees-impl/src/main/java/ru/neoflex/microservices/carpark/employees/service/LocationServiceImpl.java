package ru.neoflex.microservices.carpark.employees.service;

import static org.springframework.data.jpa.domain.Specifications.where;
import static ru.neoflex.microservices.carpark.employees.repository.LocationSpecifications.locationIsActive;
import static ru.neoflex.microservices.carpark.employees.repository.LocationSpecifications.locationLikeAddresses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.employees.dto.LocationCommand;
import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.employees.model.LocationFilter;
import ru.neoflex.microservices.carpark.employees.repository.LocationRepository;
import ru.neoflex.microservices.carpark.employees.sender.Sender;

import java.util.List;

/**
 * Service for location.
 *
 * @author mirzoevnik
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
    public void add(Location location) {
        locationRepository.save(location);
        LocationCommand locationCommand = new LocationCommand();
        locationCommand.setCommand(Command.ADD);
        locationCommand.setEntity(location);
        sender.send(locationTopic, locationCommand);
    }

    @Override
    public void update(Location location) {
        Location oldLocation = locationRepository.findOne(location.getId());
        locationRepository.save(location);
        LocationCommand locationCommand = new LocationCommand();
        locationCommand.setCommand(Command.UPDATE);
        locationCommand.setEntity(location);
        locationCommand.setOldEntity(oldLocation);
        sender.send(locationTopic, locationCommand);
    }

    @Override
    public List<Location> getAll(LocationFilter filter) {
        return locationRepository.findAll(where(locationLikeAddresses(filter))
                .and(locationIsActive(filter))
                .and(locationLikeAddresses(filter)));
    }
}
