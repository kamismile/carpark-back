/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.model;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Entity for rubric.
 *
 * @author Mirzoev_Nikita
 */
@Entity
@Table(name = "DICT_RUBRIC")
@Data
public class Rubric implements Serializable {

    @Id
    private String code;

    private String title;

    private boolean system;
}
