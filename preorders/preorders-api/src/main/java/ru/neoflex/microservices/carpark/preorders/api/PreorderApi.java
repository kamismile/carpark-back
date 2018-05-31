package ru.neoflex.microservices.carpark.preorders.api;

import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface PreorderApi {

    List<Preorder> getPreorders();
}
