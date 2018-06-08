/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vtb.microservices.carpark.employees.api.LocationApi;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;
import ru.vtb.microservices.carpark.employees.service.LocationService;

import java.util.List;

/**
 * Controller for locations.
 *
 * @author Mirzoev_Nikita
 */
@RestController
@Api(value = "locations", description = "Rest API for locations operations", tags = "Locations API")
public class LocationController implements LocationApi {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get location by id")
    public Location getById(Long id) {
        return locationService.getById(id);
    }

    @DeleteMapping(value = "/location/deactivate/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deactivate location by id")
    public void deactivate(@PathVariable("locationId") Long locationId) {
        locationService.deactivate(locationId);
    }

    @PostMapping(value = "/location/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new location")
    public void add(@RequestBody Location location) {
        locationService.add(location);
    }

    @PutMapping(value = "/location/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update location")
    public void update(@RequestBody Location location) {
        locationService.add(location);
    }

    @GetMapping(value = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all locations")
    public List<Location> getAll(LocationFilter filter) {
        return locationService.getAll(filter);
    }
}
