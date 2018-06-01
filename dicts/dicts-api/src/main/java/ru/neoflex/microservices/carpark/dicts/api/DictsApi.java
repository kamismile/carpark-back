package ru.neoflex.microservices.carpark.dicts.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface DictsApi {
    @GetMapping(value = "/rubrics", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Rubric> getRubrics();

    @GetMapping(value = "/references/{rubricCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Reference> getReferencesByRubric(@PathVariable("rubricCode") String rubricCode);
}
