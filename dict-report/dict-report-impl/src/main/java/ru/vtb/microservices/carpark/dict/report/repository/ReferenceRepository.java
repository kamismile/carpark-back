/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dict.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.dicts.model.Reference;

/**
 * Repository for reference.
 *
 * @author Vadim_Nasonov
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
