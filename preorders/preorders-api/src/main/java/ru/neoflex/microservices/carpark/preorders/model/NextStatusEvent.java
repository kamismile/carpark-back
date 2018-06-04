package ru.neoflex.microservices.carpark.preorders.model;

import lombok.AllArgsConstructor;
import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;

@AllArgsConstructor
public class NextStatusEvent extends KafkaCommand<NextStatus> {
}
