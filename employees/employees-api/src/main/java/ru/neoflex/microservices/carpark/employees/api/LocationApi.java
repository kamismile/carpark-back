package ru.neoflex.microservices.carpark.employees.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.neoflex.microservices.carpark.employees.model.Location;

/**
 * @author mirzoevnik
 */
public interface LocationApi {

    @GetMapping(value = "/location/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Location getById(@PathVariable Long id);
}
