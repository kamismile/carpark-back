package ru.neoflex.microservices.carpark.employees.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.employees.model.Location;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface LocationApi {

    @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Location getById(@PathVariable Long id);

    @DeleteMapping(value = "/location/deactivate/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deactivate(@PathVariable("locationId") Long locationId);

    @PutMapping(value = "/location/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void add(Location location);

    @PatchMapping(value = "/location/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void update(Location location);

    @GetMapping(value = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Location> getAll();

}
