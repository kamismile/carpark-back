package ru.vtb.microservices.carpark.employees.service;

import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;

import java.util.List;

/**
 * Service for location.
 * @author mirzoevnik
 */
public interface LocationService {

    Location getById(Long id);

    void deactivate(Long locationId);

    public Location add(Location location);

    public void update(Location location);

    List<Location> getAll(LocationFilter filter);
}
