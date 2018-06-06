package ru.neoflex.microservices.carpark.dicts.controller.api;

import ru.neoflex.microservices.carpark.dicts.controller.model.Reference;
import ru.neoflex.microservices.carpark.dicts.controller.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface DictsApi {

    List<Rubric> getRubrics();

    List<Reference> getReferencesByRubric(String rubricCode);

    void updateReference(String referenceCode, Reference reference);

    void createReference(Reference reference);

    void disableReference(String referenceCode);

    void updateRubric(String rubricCode, Rubric rubric);

    void createRubric(Rubric rubric);

}