package ru.vtb.microservices.carpark.employees.dto;

import ru.vtb.microservices.carpark.commons.model.KafkaCommand;
import ru.vtb.microservices.carpark.employees.model.Location;

/**
 * Wraper for Location kafka message.
 *
 * @author rmorenko
 */
public class LocationCommand extends KafkaCommand<Location> {
}
