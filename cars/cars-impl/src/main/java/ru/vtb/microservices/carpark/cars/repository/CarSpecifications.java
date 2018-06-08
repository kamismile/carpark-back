/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * SpringData specifications for location filter.
 *
 * @author Roman_Morenko
 */

public class CarSpecifications {

    private static final Set<String> ALL_PERMISION_ROLES =
            new HashSet<>(Arrays.asList("management", "administrator"));

    private CarSpecifications() {
        super();
    }


    public static Specification<Car> accessSpecifications(UserInfo userInfo) {

        return (root, query, cb) -> {
            if (ALL_PERMISION_ROLES.contains(userInfo.getRole())) {
                return null;
            }
            if ("rental_manager".equals(userInfo.getRole())) {
                return cb.equal(root.get("localityId"), userInfo.getLocationId());
            }
            if ("service_manager".equals(userInfo.getRole())) {
                Predicate curentStatus = cb.equal(root.get("currentStatus"), "in_service");
                Predicate nextStatus = cb.equal(root.get("nextStatus"), "in_service");
                return cb.or(curentStatus, nextStatus);
            }
            return null;
        };
    }

    public static Specification<Car> carIsYearFrom(CarFilter filter) {
        return (root, query, cb) -> {
            if (filter.getYearFrom() == null) {
                return null;
            }
            return cb.greaterThan(root.get("year"), filter.getYearFrom());
        };
    }

    public static Specification<Car> carIsYearTo(CarFilter filter) {
        return (root, query, cb) -> {
            if (filter.getYearFrom() == null) {
                return null;
            }
            return cb.lessThan(root.get("year"), filter.getYearTo());
        };
    }

    public static Specification<Car> carIsCurrentLocationId(CarFilter filter) {
        return (root, query, cb) -> {
            if (filter.getCurrentLocationId() == null) {
                return null;
            }
            return cb.equal(root.get("currentLocationId"), filter.getCurrentLocationId());
        };
    }

    public static Specification<Car> carIsLocationId(CarFilter filter) {
        return (root, query, cb) -> {
            if (filter.getCurrentLocationId() == null) {
                return null;
            }
            return cb.equal(root.get("locationId"), filter.getLocationId());
        };
    }

    public static Specification<Car> carIsMileageFrom(CarFilter filter) {
        return (root, query, cb) -> {
            if (filter.getMileageFrom() == null) {
                return null;
            }
            return cb.greaterThan(root.get("mileage"), filter.getMileageFrom());
        };
    }

    public static Specification<Car> carIsMileageTo(CarFilter filter) {
        return (root, query, cb) -> {
            if (filter.getMileageTo() == null) {
                return null;
            }
            return cb.lessThan(root.get("mileage"), filter.getMileageTo());
        };
    }


    public static Specification<Car> carInCurentStatuses(CarFilter filter) {
        return (root, query, cb) -> {
            if (ObjectUtils.isEmpty(filter.getCurrentStatuses())) {
                return null;
            }
            if (filter.getCurrentStatuses().get(0).isEmpty()) {
                return null;
            }
            return root.get("currentStatus").in(filter.getCurrentStatuses());
        };
    }


    public static Specification<Car> carInMarks(CarFilter filter) {
        return (root, query, cb) -> {
            if (ObjectUtils.isEmpty(filter.getMarks())) {
                return null;
            }
            if (filter.getMarks().get(0).isEmpty()) {
                return null;
            }
            return root.get("mark").in(filter.getMarks());
        };
    }
}

