/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.api;

import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * Api for Dicts.
 *
 * @author Nikita_Mirzoev
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