package ru.neoflex.microservices.carpark.dicts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.dicts.api.DictsApi;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceService;
import ru.neoflex.microservices.carpark.dicts.service.RubricService;

import java.util.List;

/**
 * @author mirzoevnik
 */
@RestController
public class DictsController implements DictsApi {

    private final RubricService rubricService;
    private final ReferenceService referenceService;


    @Autowired
    public DictsController(RubricService rubricService, ReferenceService referenceService) {
        this.rubricService = rubricService;
        this.referenceService = referenceService;
    }

    @GetMapping(value = "/rubrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rubric> getRubrics() {
        return rubricService.findAll();
    }

    @GetMapping(value = "/references/{rubricCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasPermission({'rubricCode', #rubricCode}, {'getReferencesByRubric'})")
    @PostFilter("hasPermission(filterObject, {'getReferencesByRubric_filter', {'rubricCode',#rubricCode}})")
    public List<Reference> getReferencesByRubric(@PathVariable String rubricCode) {
        Rubric rubric = rubricService.findByCode(rubricCode);
        return referenceService.findByRubric(rubric);
    }


}
