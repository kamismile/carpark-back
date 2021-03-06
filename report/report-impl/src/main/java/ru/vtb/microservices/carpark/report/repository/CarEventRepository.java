/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.report.model.CarEvent;

import java.util.Date;
import java.util.List;

/**
 * Repository for repository.
 *
 * @author Roman_Morenko
 */
@Repository
public interface CarEventRepository extends JpaRepository<CarEvent, Long>, JpaSpecificationExecutor {

    public List<CarEvent> findByMessageDate(Date date);

    public List<CarEvent> findByMessageDateAndLocationId(Date date, Long locationId);
}
