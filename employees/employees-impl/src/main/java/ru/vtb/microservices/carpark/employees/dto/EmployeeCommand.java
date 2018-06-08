package ru.vtb.microservices.carpark.employees.dto;

import ru.vtb.microservices.carpark.commons.model.KafkaCommand;
import ru.vtb.microservices.carpark.employees.model.Employee;

/**
 * Wraper for Employee kafka message.
 *
 * @author rmorenko
 */
public class EmployeeCommand extends KafkaCommand<Employee> {

}
