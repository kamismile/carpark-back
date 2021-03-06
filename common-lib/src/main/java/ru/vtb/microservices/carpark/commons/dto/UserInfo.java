/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * User info dto.
 *
 * @author Mirzoev_Nikita
 */
@Data
@AllArgsConstructor
@Setter
public class UserInfo implements Serializable {

    private String name;

    private String role;

    private Long locationId;

    public UserInfo() {
        super();
    }
}