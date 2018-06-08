/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * Объект доступа к данным о презаказх.
 *
 * @author Denis_Begun
 */
@Repository
public interface PreorderRepository extends JpaRepository<Preorder, Long> {

    List<Preorder> findByCarId(Long carId);
}
