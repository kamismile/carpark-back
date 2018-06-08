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
import javax.persistence.Table;

/**
 * Сущность для хранения информации о возомжных переходах между состояниями.
 *
 * @author Denis_Begun
 */
@Data
@Entity
@Table(name = "transition")
public class Transition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "FROM_STATE")
    private States from;

    @Enumerated(EnumType.STRING)
    @Column(name = "TO_STATE")
    private States to;

    @Enumerated(EnumType.STRING)
    private Events event;
}