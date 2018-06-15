/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.command;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vtb.microservices.carpark.access.feign.AccessExpressionFeign;
import ru.vtb.microservices.carpark.access.model.AccessExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Hystrix Command for fill security rules.
 *
 * @author Nikita_Mirzoev
 */
public class AccessExpressionCommand {

    @Autowired
    private AccessExpressionFeign accessExpressionFeign;

    private static final List<AccessExpression> DEFAULT_ACCESS_EXPR;

    public static final String USER_INFO_ROLE_ADMINISTRATOR = "#userInfo.role == 'administrator'";

    static {
        DEFAULT_ACCESS_EXPR = new ArrayList<>();
        DEFAULT_ACCESS_EXPR.add(new AccessExpression("getCars_filter",
                " ( #userInfo.role == 'rental_manager' &&" +
                        " ( #userInfo.locationId == #target.currentLocationId || #userInfo.locationId == #target.locationId ) ) "
                + " || ( #userInfo.role == 'service_manager' && ( #target.currentStatus = 'in_service' ||  #target.nextStatus == 'in_service' )) "
                + " || #userInfo.role == 'management' ||  #userInfo.role == 'administrator' "));
        DEFAULT_ACCESS_EXPR.add((new AccessExpression("getReferencesByRubric_filter", "#userInfo.role != 'test'")));
        DEFAULT_ACCESS_EXPR.add((new AccessExpression("changeCarState", "#userInfo.role == 'management' || #userInfo.role == 'administrator' "
                + "|| ( #userInfo.role == 'rental_manager' "
                + " && ( #stringEvent == 'READY' || #stringEvent == 'IN_USE' ) )|| "
                + " ( #userInfo.role == 'service_manager' &&  #stringEvent == 'IN_SERVICE') ")));
        DEFAULT_ACCESS_EXPR.add((new AccessExpression("deleteCar", USER_INFO_ROLE_ADMINISTRATOR)));
        DEFAULT_ACCESS_EXPR.add((new AccessExpression("createCar", USER_INFO_ROLE_ADMINISTRATOR)));
        DEFAULT_ACCESS_EXPR.add((new AccessExpression("updateCar", USER_INFO_ROLE_ADMINISTRATOR)));

    }
    @SuppressWarnings("all")
    @HystrixCommand(fallbackMethod = "defaultList", commandKey = "AccessExpressionCommand")
    public AccessExpression getByOperation(String operation) {
        return accessExpressionFeign.getByOperation(operation);
    }

    public List<AccessExpression> getAll() {
        return accessExpressionFeign.getAll();
    }

    @SuppressWarnings("all")
    public AccessExpression defaultList(String operation) {
        return DEFAULT_ACCESS_EXPR.stream()
                .filter( o -> o.getOperation().equals(operation))
                .findFirst().orElse(
                        new AccessExpression(operation,"#userInfo.role != 'fake'"));
    }
}