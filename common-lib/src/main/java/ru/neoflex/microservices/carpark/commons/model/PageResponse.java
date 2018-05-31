package ru.neoflex.microservices.carpark.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * @author mirzoevnik
 */
@Getter
@AllArgsConstructor
public class PageResponse<T> {

    private final Set<T> data;

    private final long total;
}