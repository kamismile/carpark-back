package ru.vtb.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import ru.vtb.microservices.carpark.commons.model.KafkaCommand;

@AllArgsConstructor
public class NextStatusEvent extends KafkaCommand<NextStatus> {
}
