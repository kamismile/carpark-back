/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * Repository for reference.
 *
 * @author Roman_Morenko
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, String> {

    List<Reference> findByRubric(Rubric rubric);

    Reference findByCode(String code);
}
