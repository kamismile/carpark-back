package ru.neoflex.microservices.carpark.cars.model;

import lombok.Data;


import javax.persistence.*;
import java.util.Date;

/**
 * Сущность автомобиля.
 * @author mirzoevnik
 */
@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mark;

    private Integer year;

    private Double mileage;

    @Column(name = "PREV_MAINTENANCE_DATE")
    private Date prevMaintenanceDate;

    @Column(name = "NEXT_MAINTENANCE_DATE")
    private Date nextMaintenanceDate;

    @Column(name = "CURRENT_STATUS")
    private String currentStatus;

    @Column(name = "CURRENT_STATUS_DATE")
    private Date currentStatusDate;

    @Column(name = "NEXT_STATUS")
    private String nextStatus;

    @Column(name = "NEXT_STATUS_DATE")
    private Date nextStatusDate;

    private String state;

    @Column (name = "LOCATION_ID")
    private Long locationId;

    @Column (name = "CURRENT_LOCATION_ID")
    private Long currentLocationId;

    @Column (name = "REG_NUMBER")
    private String number;

    public States getState() {
        return States.valueOf(state);
    }

    public void setState(States stateEnumVal) {
        state = stateEnumVal.name();
    }
}
