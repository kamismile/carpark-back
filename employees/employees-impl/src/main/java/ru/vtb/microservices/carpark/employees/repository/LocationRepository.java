/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.employees.model.Location;

/**
 * Repository for locations.
 *
 * @author Mirzoev_Nikita
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long>,
        JpaSpecificationExecutor {
}
