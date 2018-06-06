package ru.neoflex.microservices.carpark.employees.service;

import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.employees.model.LocationFilter;

import java.util.List;

/**
 * Service for location.
 * @author mirzoevnik
 */
public interface LocationService {

    Location getById(Long id);

    void deactivate(Long locationId);

    public void add(Location location);

    public void update(Location location);

    List<Location> getAll(LocationFilter filter);
}
