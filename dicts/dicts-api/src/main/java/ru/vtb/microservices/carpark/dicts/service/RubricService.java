/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.service;

import ru.vtb.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * Service for rubric.
 *
 * @author Mirzoev_Nikita
 */
public interface RubricService {

    List<Rubric> findAll();

    Rubric findByCode(String code);

    void createRubric(Rubric rubric);

    void updateRubric(Rubric rubric);

}
