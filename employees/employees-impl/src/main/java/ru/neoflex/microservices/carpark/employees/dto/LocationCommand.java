package ru.neoflex.microservices.carpark.employees.dto;

import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;
import ru.neoflex.microservices.carpark.employees.model.Location;

/**
 * @author rmorenko
 */
public class LocationCommand extends KafkaCommand<Location> {
}
