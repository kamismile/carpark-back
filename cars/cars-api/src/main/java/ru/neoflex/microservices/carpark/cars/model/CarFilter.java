package ru.neoflex.microservices.carpark.cars.model;

import lombok.Data;

import java.util.List;

/**
 * Filter for car.
 *
 * @author rmorenko
 */
@Data
public class CarFilter {

        private List<String> marks;

        private Integer yearFrom;

        private Integer yearTo;

        private Long currentLocationId;

        private Double mileageFrom;

        private Double mileageTo;

        private Long locationId;

        private List<String> currentStatuses;

}
