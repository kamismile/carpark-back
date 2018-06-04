package ru.neoflex.microservices.carpark.dicts.api;

import org.springframework.web.bind.annotation.PathVariable;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;

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