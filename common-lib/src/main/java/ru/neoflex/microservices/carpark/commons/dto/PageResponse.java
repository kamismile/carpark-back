package ru.neoflex.microservices.carpark.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author rmorenko
 */
@Data
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> data;

    private long total;
}
