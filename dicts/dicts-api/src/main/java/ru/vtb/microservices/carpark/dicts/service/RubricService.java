package ru.vtb.microservices.carpark.dicts.service;

import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface RubricService {

    List<Rubric> findAll();

    Rubric findByCode(String code);

    void createRubric(Rubric rubric);

    void updateRubric(Rubric rubric);

}