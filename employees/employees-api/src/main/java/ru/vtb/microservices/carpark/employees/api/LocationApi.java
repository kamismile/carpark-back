/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.api;

import org.springframework.web.bind.annotation.PathVariable;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;

import java.util.List;

/**
 * Api for locations.
 *
 * @author Nikita_Mirzoev
 */
public interface LocationApi {

    Location getById(@PathVariable Long id);

    void deactivate(@PathVariable("locationId") Long locationId);

    void add(Location location);

    void update(Location location);

    List<Location> getAll(LocationFilter filter);
}
