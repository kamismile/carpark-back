package ru.neoflex.microservices.carpark.employees.service;

import ru.neoflex.microservices.carpark.employees.model.Location;

/**
 * @author mirzoevnik
 */
public interface LocationService {

    Location getById(Long id);
}
