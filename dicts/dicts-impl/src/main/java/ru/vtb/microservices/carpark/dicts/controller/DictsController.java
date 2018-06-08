/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vtb.microservices.carpark.dicts.api.DictsApi;
import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.service.ReferenceService;
import ru.vtb.microservices.carpark.dicts.service.RubricService;

import java.util.List;

/**
 * Controller for dicts.
 *
 * @author Nikita_Mirzoev
 */
@RestController
@Api(value = "cars", description = "Rest API for dict operations", tags = "Dicts API")
public class DictsController implements DictsApi {

    private final RubricService rubricService;
    private final ReferenceService referenceService;


    @Autowired
    public DictsController(RubricService rubricService, ReferenceService referenceService) {
        this.rubricService = rubricService;
        this.referenceService = referenceService;
    }

    @GetMapping(value = "/rubrics", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all rubrics")
    public List<Rubric> getRubrics() {
        return rubricService.findAll();
    }

    @GetMapping(value = "/references/{rubricCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasPermission({'rubricCode', #rubricCode}, {'getReferencesByRubric'})")
    @PostFilter("hasPermission(filterObject, {'getReferencesByRubric_filter', {'rubricCode',#rubricCode}})")
    @ApiOperation(value = "Get references by rubric")
    public List<Reference> getReferencesByRubric(@PathVariable String rubricCode) {
        Rubric rubric = rubricService.findByCode(rubricCode);
        return referenceService.findByRubric(rubric);
    }

    @PutMapping(value = "/references/{referenceCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a reference")
    public void updateReference(@PathVariable String referenceCode, @RequestBody Reference reference) {
        reference.setCode(referenceCode);
        referenceService.updateReference(reference);
    }

    @PostMapping(value = "/references",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new reference")
    public void createReference(@RequestBody Reference reference) {
        referenceService.createReference(reference);
    }

    @DeleteMapping(value = "/references/{referenceCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Disable reference by code")
    public void disableReference(@PathVariable String referenceCode) {
        referenceService.disableReference(referenceCode);
    }

    @PutMapping(value = "/rubrics/{rubricCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update rubric by code")
    public void updateRubric(@PathVariable String rubricCode, @RequestBody Rubric rubric) {
        rubric.setCode(rubricCode);
        rubricService.updateRubric(rubric);
    }

    @PostMapping(value = "/rubrics", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new rubric")
    public void createRubric(@RequestBody Rubric rubric) {
        rubricService.createRubric(rubric);
    }

}
