/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dict.report.model;

import ru.vtb.microservices.carpark.commons.model.KafkaCommand;
import ru.vtb.microservices.carpark.employees.model.Employee;

/**
 * Command for Employee.
 *
 * @author Roman_Morenko
 */
public class EmployeeCommand extends KafkaCommand<Employee> {

}
