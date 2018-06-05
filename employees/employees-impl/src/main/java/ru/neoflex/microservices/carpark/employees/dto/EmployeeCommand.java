package ru.neoflex.microservices.carpark.employees.dto;

import ru.neoflex.microservices.carpark.commons.model.KafkaCommand;
import ru.neoflex.microservices.carpark.employees.model.Employee;

/**
 * Wraper for Employee kafka message.
 *
 * @author rmorenko
 */
public class EmployeeCommand extends KafkaCommand<Employee> {

}
