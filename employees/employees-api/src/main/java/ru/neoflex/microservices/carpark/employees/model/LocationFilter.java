package ru.neoflex.microservices.carpark.employees.model;

import lombok.Data;

import java.util.List;

/**
 * Filter for locations api.
 *
 * @author rmorenko
 */
@Data
public class LocationFilter {

    private String address;

    private List<String> locationTypes;

    private Boolean active;


}
