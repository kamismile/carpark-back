package ru.vtb.microservices.carpark.employees.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;
import ru.vtb.microservices.carpark.employees.model.Location;
import ru.vtb.microservices.carpark.employees.model.LocationFilter;

import java.util.List;

/**
 * Api for locations.
 * @author mirzoevnik
 */
public interface LocationApi {

    Location getById(@PathVariable Long id);

    void deactivate(@PathVariable("locationId") Long locationId);

    void add(Location location);

    void update(Location location);

    List<Location> getAll(LocationFilter filter);
}
