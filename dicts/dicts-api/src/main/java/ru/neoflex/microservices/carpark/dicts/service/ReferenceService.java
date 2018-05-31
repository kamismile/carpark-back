package ru.neoflex.microservices.carpark.dicts.service;

import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface ReferenceService {

    List<Reference> findByRubric(Rubric rubric);
}
