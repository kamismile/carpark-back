/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.service;

import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;

import java.util.List;

/**
 * Service for location.
 *
 * @author Nikita_Mirzoev
 */
public interface LocationService {

    Location getById(Long id);

    void deactivate(Long locationId);

    public Location add(Location location);

    public void update(Location location);

    List<Location> getAll(LocationFilter filter);
}
