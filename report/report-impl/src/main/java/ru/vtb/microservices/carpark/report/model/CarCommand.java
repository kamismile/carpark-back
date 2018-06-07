package ru.vtb.microservices.carpark.report.model;

import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.commons.model.KafkaCommand;

/**
 * @author rmorenko
 */
public class CarCommand extends KafkaCommand<Car> {
}
