package ru.neoflex.microservices.carpark.dicts.controller.service;

import ru.neoflex.microservices.carpark.dicts.controller.model.Reference;
import ru.neoflex.microservices.carpark.dicts.controller.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface ReferenceService {

    List<Reference> findByRubric(Rubric rubric);

    void createReference(Reference reference);

    void updateReference(Reference reference);

    void disableReference(String code);
}
