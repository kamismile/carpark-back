package ru.neoflex.microservices.carpark.employees.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilter;
import ru.neoflex.microservices.carpark.employees.model.Location;
import ru.neoflex.microservices.carpark.employees.model.LocationFilter;

public class LocationSpecifications {

    private LocationSpecifications(){
        super();
    }

    public static Specification<Location> locationLikeAddresses(LocationFilter filter) {
        return (root, query, cb) -> {
            if (filter.getAddress() == null) {
                return null;
            }
            return cb.like(root.get("address"), filter.getAddress());
        };
    }

    public static Specification<Location> locationIsActive(LocationFilter filter) {
        return (root, query, cb) -> {
            if (filter.getActive() == null) {
                return null;
            }
            return cb.like(root.get("active"), filter.getAddress());
        };
    }

    public static Specification<Location> employeeInLocationTypes(LocationFilter filter){
        return (root, query, cb) -> {
            if (ObjectUtils.isEmpty(filter.getLocationTypes())){
                return null;
            }
            return  root.get("locationType").in(filter.getLocationTypes());
        };
    }
}
