package ru.neoflex.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;

@AllArgsConstructor
public class CarEvent extends KafkaCommand<Car> {
}
