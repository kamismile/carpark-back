package ru.neoflex.microservices.carpark.commons.dto;

import java.util.List;

/**
 * @author rmorenko
 */
public class Page<T> {

    private List<T> data;

    private long total;

}
