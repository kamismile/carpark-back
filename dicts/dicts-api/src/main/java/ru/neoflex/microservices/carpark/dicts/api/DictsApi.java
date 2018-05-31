package ru.neoflex.microservices.carpark.dicts.api;

import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface DictsApi {

    List<Rubric> getRubrics();

    List<Reference> getReferencesByRubric(String rubricCode);
}
