package ru.neoflex.microservices.carpark.commons.command;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neoflex.microservices.carpark.access.feign.AccessExpressionFeign;
import ru.neoflex.microservices.carpark.access.model.AccessExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirzoevnik
 */
public class AccessExpressionCommand {

    @Autowired
    private AccessExpressionFeign accessExpressionFeign;

    private static final List<AccessExpression> defaultAccessExpressions;

    public static final String USER_INFO_ROLE_ADMINISTRATOR = "#userInfo.role == 'administrator'";

    static {
        defaultAccessExpressions = new ArrayList<>();
        defaultAccessExpressions.add(new AccessExpression("getCars_filter",
            " ( #userInfo.role == 'rental_manager' && #userInfo.localityId == #target.currentLocationId ) "
            + " || ( #userInfo.role == 'service_manager' && ( #target.currentStatus = 'in_service' ||  #target.nextStatus == 'in_service' )) "
            + " || #userInfo.role == 'management' ||  #userInfo.role == 'administrator' "));
        defaultAccessExpressions.add((new AccessExpression("getReferencesByRubric_filter", "#userInfo.role != 'test'")));
        defaultAccessExpressions.add((new AccessExpression("changeCarState", "#userInfo.role == 'management'")));
        defaultAccessExpressions.add((new AccessExpression("deleteCar", USER_INFO_ROLE_ADMINISTRATOR)));
        defaultAccessExpressions.add((new AccessExpression("createCar", USER_INFO_ROLE_ADMINISTRATOR)));
        defaultAccessExpressions.add((new AccessExpression("updateCar", USER_INFO_ROLE_ADMINISTRATOR)));
    }

    @HystrixCommand(fallbackMethod = "defaultList", commandKey = "AccessExpressionCommand")
    public List<AccessExpression> getAll() {
        return accessExpressionFeign.getAll();
    }

    public List<AccessExpression> defaultList() {
        System.out.println("defaultList");
        return defaultAccessExpressions;
    }
}
