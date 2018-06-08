/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.microservices.carpark.employees.model.Location;

/**
 * Repository ror location.
 *
 * @author Roman_Morenko
 */
public interface LocationRepository extends JpaRepository<Location,Long> {

    Location findByAddress(String address);
}
