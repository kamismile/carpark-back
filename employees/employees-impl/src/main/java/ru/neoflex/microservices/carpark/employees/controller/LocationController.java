package ru.neoflex.microservices.carpark.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.employees.api.LocationApi;
import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.employees.model.LocationFilter;
import ru.neoflex.microservices.carpark.employees.service.LocationService;

import java.util.List;

/**
 * @author mirzoevnik
 */
@RestController
public class LocationController implements LocationApi {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public Location getById(Long id) {
        return locationService.getById(id);
    }

    @Override
    public void deactivate(@PathVariable("locationId") Long locationId) {
         locationService.deactivate(locationId);
    }

    @Override
    public void add(@RequestBody Location location) {
       locationService.add(location);
    }

    @Override
    public void update(@RequestBody Location location) {
        locationService.add(location);
    }

    @Override
    public List<Location> getAll(LocationFilter filter) {
       return locationService.getAll(filter);
    }


}
