/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.service;

import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * Service for reference.
 *
 * @author Mirzoev_Nikita
 */
public interface ReferenceService {

    List<Reference> findByRubric(Rubric rubric);

    void createReference(Reference reference);

    void updateReference(Reference reference);

    void disableReference(String code);
}
