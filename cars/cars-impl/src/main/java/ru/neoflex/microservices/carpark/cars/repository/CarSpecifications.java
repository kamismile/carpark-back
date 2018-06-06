package ru.neoflex.microservices.carpark.cars.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.cars.model.CarFilter;


/**
 * SpringData specifications for location filter.
 *
 * @author rmorenko
 */

public class CarSpecifications {

    private CarSpecifications() {
        super();
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
            return  root.get("currentStatus").in(filter.getCurrentStatuses());
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
            return  root.get("mark").in(filter.getMarks());
        };
    }
}

