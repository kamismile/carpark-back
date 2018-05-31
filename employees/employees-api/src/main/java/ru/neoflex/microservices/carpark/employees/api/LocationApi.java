package ru.neoflex.microservices.carpark.employees.api;

import ru.neoflex.microservices.carpark.employees.model.Location;

/**
 * @author mirzoevnik
 */
public interface LocationApi {

    Location getById(Long id);
}
