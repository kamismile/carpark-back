/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.vtb.microservices.carpark.access.model.AccessExpression;

import java.util.List;

/**
 * Springdata jpa repository for Access expression.
 *
 * @author Roman_Morenko
 */

public interface AccessExpressionRepository extends JpaRepository<AccessExpression, Long>, CrudRepository<AccessExpression, Long> {
    List<AccessExpression> findByOperation(String operation);
}
