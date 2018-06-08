/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Location entity.
 *
 * @author Mirzoev_Nikita
 */
@Data
@Entity
@Table
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS", length = 300)
    private String address;

    @Column(name = "LOCATION_TYPE")
    private String locationType;

    private boolean active;

}
