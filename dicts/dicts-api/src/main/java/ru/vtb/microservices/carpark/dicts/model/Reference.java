/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * Reference model.
 *
 * @author Mirzoev_Nikita
 */
@Entity
@Table(name = "DICT_REFERENCE")
@Data
public class Reference implements Serializable {

    @Id
    private String code;

    private String title;

    private boolean system;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "RUBRIC_CODE")
    private Rubric rubric;
}
