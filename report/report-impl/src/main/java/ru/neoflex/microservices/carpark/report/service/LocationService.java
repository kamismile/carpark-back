package ru.neoflex.microservices.carpark.report.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.report.model.LocationCommand;
import ru.neoflex.microservices.carpark.report.repository.LocationRepository;

/**
 * @author rmorenko
 */
@Service
@Slf4j
@Transactional
public class LocationService {

    @Autowired
    LocationRepository repository;

    public void save(LocationCommand cmd) {
        if (Command.DELETE.equals(cmd.getCommand())) {
            cmd.getEntity().setActive(false);
            repository.save(cmd.getEntity());
        } else if (Command.ADD.equals(cmd.getCommand())) {
            cmd.getEntity().setId(null);
            repository.save(cmd.getEntity());
        } else {
            Location oldLocation =  repository.findByAddress(cmd.getOldEntity().getAddress());
            repository.delete(oldLocation);
            cmd.getEntity().setId(null);
            repository.save(cmd.getEntity());
        }
    }


}
