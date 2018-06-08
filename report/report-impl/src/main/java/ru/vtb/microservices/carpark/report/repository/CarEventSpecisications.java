/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */
package ru.vtb.microservices.carpark.report.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.vtb.microservices.carpark.report.model.CarEvent;


import java.util.Date;

/**
 * Specification for CarEvent.
 *
 * @author Roman_Morenko
 */
public class CarEventSpecisications {

    public static Specification<CarEvent> carEventFrom(Date from) {
      return (root, query, cb) -> {
                              if (from == null) {
                                      return null;
                              }
                              return cb.greaterThan(root.get("messageDate"), from);
           };
      }

      public static Specification<CarEvent> carEventTo(Date to) {
                return (root, query, cb) -> {
                        if (to == null) {
                                return null;
                        }
                        return cb.lessThan(root.get("messageDate"), to);
           };
      }

}
