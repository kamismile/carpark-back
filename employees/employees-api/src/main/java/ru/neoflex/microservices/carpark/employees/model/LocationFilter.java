package ru.neoflex.microservices.carpark.employees.model;

import lombok.Data;

import java.util.List;

/**
 * @author rmorenko
 */
@Data
public class LocationFilter {

   private String address;

   private List<String> locationTypes;

   private Boolean active;


}
