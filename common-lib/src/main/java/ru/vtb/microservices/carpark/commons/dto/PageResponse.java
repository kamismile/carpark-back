/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/** Page response model.
 * @param <T> entity
 * @author Roman_Morenko
 */
@Data
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> data;

    private long total;
}
