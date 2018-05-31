package ru.neoflex.microservices.carpark.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.employees.api.LocationApi;
import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.employees.service.LocationService;

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
    @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Location getById(@PathVariable Long id) {
        return locationService.getById(id);
    }
}
