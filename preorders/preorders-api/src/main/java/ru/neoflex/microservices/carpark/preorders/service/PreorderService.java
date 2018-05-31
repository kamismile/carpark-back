package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface PreorderService {

    List<Preorder> findAll();
}
