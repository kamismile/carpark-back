/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.microservices.carpark.cars.model.Transition;

/**
 * Объект доступа к репозиторию переходов между состояниями машины состояний.
 *
 * @author Denis_Begun
 */
public interface StateMachineTransitionRepository extends JpaRepository<Transition, Long> {
}


