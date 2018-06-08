package ru.vtb.microservices.carpark.employees.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Filter for employee.
 *
 * @author rmorenko
 */
@Data
public class EmployeeFilter {

    private String name;

    private String surname;

    private String patronymic;

    private List<String> positions;

    private Date appointmentDateFrom;

    private Date appointmentDateTo;

    private List<String> locations;

    private Long userId;

    private Boolean active;



}
