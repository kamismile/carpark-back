package ru.neoflex.microservices.carpark.report.model;

import lombok.Data;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.employees.model.Employee;

/**
 * @author rmorenko
 */
@Data
public class HistoryCarModel extends Car{
  String fio;
  String address;

}
