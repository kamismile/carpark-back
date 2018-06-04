package ru.neoflex.microservices.carpark.employees.service;

import org.springframework.web.bind.annotation.PathVariable;
import ru.neoflex.microservices.carpark.employees.model.Location;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface LocationService {

    Location getById(Long id);

    void deactivate(Long locationId);

    public void add(Location location);

    public void update(Location location);

    public List<Location> getAll();
}
