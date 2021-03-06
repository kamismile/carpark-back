/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.model.Rubric;

/**
 * Rebository for rubric entity.
 *
 * @author Roman_Morenko
 */
@Repository
public interface RubricRepository extends JpaRepository<Rubric, String> {

    Rubric findByCode(String code);
}
