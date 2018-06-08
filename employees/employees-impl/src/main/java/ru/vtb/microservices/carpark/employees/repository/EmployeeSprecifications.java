/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;

/**
 * SpringData specifications for employee filter.
 *
 * @author Roman_Morenko
 */
public class EmployeeSprecifications {

    private EmployeeSprecifications() {
        super();
    }

    public static Specification<Employee> employeeLikeName(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (filter.getName() == null) {
                return null;
            }
            return  cb.like(root.get("name"), filter.getName());
        };
    }

    public static Specification<Employee> employeeLikeSurname(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (filter.getSurname() == null) {
                return null;
            }
            return  cb.like(root.get("surname"), filter.getSurname());
        };
    }

    public static Specification<Employee> employeeLikePatronymic(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (filter.getPatronymic() == null) {
                return null;
            }
            return  cb.like(root.get("patronymic"), filter.getPatronymic());
        };
    }

    public static Specification<Employee> employeeHasUserId(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (filter.getUserId() == null) {
                return null;
            }
            return  cb.equal(root.get("user").get("userId"), filter.getUserId());
        };
    }

    public static Specification<Employee> employeeIsActive(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (filter.getActive() == null) {
                return null;
            }
            return  cb.equal(root.get("active"), filter.getActive());
        };
    }

    public static Specification<Employee> employeeAfterAppointmentDate(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (filter.getAppointmentDateFrom() == null) {
                return null;
            }
            return  cb.greaterThanOrEqualTo(root.get("appointmentDate"), filter.getAppointmentDateFrom());
        };
    }

    public static Specification<Employee> employeeBeforeAppointmentDate(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (filter.getAppointmentDateTo() == null) {
                return null;
            }
            return  cb.lessThanOrEqualTo(root.get("appointmentDate"), filter.getAppointmentDateTo());
        };
    }

    public static Specification<Employee> employeeInPositions(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if ( ObjectUtils.isEmpty(filter.getPositions())) {
                return null;
            }
            if (filter.getPositions().get(0).isEmpty()) {
                return null;
            }
            return  root.get("position").in(filter.getPositions());
        };
    }

    public static Specification<Employee> employeeInLocations(EmployeeFilter filter) {

        return (root, query, cb) -> {
            if (ObjectUtils.isEmpty(filter.getLocations())) {
                return null;
            }
            if (filter.getLocations().get(0).isEmpty()) {
                return null;
            }
            return  root.get("location").get("id").in(filter.getLocations());
        };
    }

}
