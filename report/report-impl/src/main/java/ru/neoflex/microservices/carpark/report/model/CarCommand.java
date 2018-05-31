package ru.neoflex.microservices.carpark.report.model;

import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;

/**
 * @author rmorenko
 */
public class CarCommand extends KafkaCommand<Car> {
}
