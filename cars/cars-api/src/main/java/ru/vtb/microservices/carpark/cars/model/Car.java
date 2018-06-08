/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Сущность автомобиля.
 *
 * @author Mirzoev_Nikita
 */
@Data
@Entity
@Table(name = "car")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Car implements Serializable {

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

    @Enumerated(EnumType.STRING)
    private States state;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "CURRENT_LOCATION_ID")
    private Long currentLocationId;

    @Column(name = "REG_NUMBER")
    private String number;

    @Transient
    private List<Events> availableEvents;
}
