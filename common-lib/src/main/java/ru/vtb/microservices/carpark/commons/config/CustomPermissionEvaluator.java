/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import ru.vtb.microservices.carpark.access.model.AccessExpression;
import ru.vtb.microservices.carpark.commons.command.AccessExpressionCommand;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.commons.util.UserInfoUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rmorenko
 */
@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {

    public static final String USER_INFO_VAR = "userInfo";
    public static final String FILTER_SUFF = "_filter";
    public static final String TARGET = "target";
    private static final Map<String, String> expressions;
    private static final Map<String, String> defaultExpressions;

    @Autowired
    private AccessExpressionCommand accessExpressionCommand;

    public static final String USER_INFO_ROLE_ADMINISTRATOR1 = "#userInfo.role == 'administrator'";
    public static final String USER_INFO_ROLE_ADMINISTRATOR = USER_INFO_ROLE_ADMINISTRATOR1;

    static {
        expressions = new ConcurrentHashMap<>();
        defaultExpressions = new ConcurrentHashMap<>();
        defaultExpressions.put("getCars_filter",
                " ( #userInfo.role == 'rental_manager' && #userInfo.locationId == #target.currentLocationId ) "
                        + " || ( #userInfo.role == 'service_manager' && ( #target.currentStatus == 'in_service' ||  #target.nextStatus == 'in_service' )) "
                        + " || #userInfo.role == 'management' ||  #userInfo.role == 'administrator' ");
        defaultExpressions.put("getReferencesByRubric_filter", "#userInfo.role != 'test'");
        defaultExpressions.put("changeCarState", "#userInfo.role == 'administrator' || #userInfo.role == 'management' "
                + "|| ( #userInfo.role == 'rental_manager' "
                + "&&  ( #stringEvent == 'READY' || #stringEvent == 'IN_USE' ) )|| ( #userInfo.role == 'service_manager' "
                + "&&  #stringEvent == 'IN_SERVICE')");
        defaultExpressions.put("deleteCar", USER_INFO_ROLE_ADMINISTRATOR1);
        defaultExpressions.put("createCar", USER_INFO_ROLE_ADMINISTRATOR1);
        defaultExpressions.put("updateCar", USER_INFO_ROLE_ADMINISTRATOR);
        defaultExpressions.put("createPreorder", "#userInfo.role == 'administrator' || #userInfo.role == 'rental_manager'");

    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject,
                                 Object permission) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        String method = ((List) permission).get(0).toString();
        UserInfo userInfo = UserInfoUtil.getInstance(authentication);
        context.setVariable(USER_INFO_VAR, userInfo);
        if (!method.contains(FILTER_SUFF)) {
            putArgumentsInContext((List) targetDomainObject, context);
        } else {
            context.setVariable(TARGET, targetDomainObject);
            List arguments = ((List) permission).subList(1, ((List) permission).size());
            putArgumentsInContext(arguments, context);
        }
        initExpressionsIfNeedForDemo(method);
        String expression = expressions.get(method);
        if (!StringUtils.isEmpty(expression)) {
            return (Boolean) parser.parseExpression(expression).getValue(context);
        }
        return true;
    }

    protected void initExpressionsIfNeed() {
        List<AccessExpression> accessExpressions = accessExpressionCommand.getAll();
        accessExpressions.stream()
                .filter(e -> !StringUtils.isEmpty(e.getOperation())
                        && !StringUtils.isEmpty(e.getExpression()))
                .forEach(e -> expressions.put(e.getOperation(), e.getExpression()));
        if (expressions.isEmpty()) {
            expressions.putAll(defaultExpressions);
        }
    }

    //TODO: special for demonstration Hystrix-Turbine
    private void initExpressionsIfNeedForDemo(String operation) {
        AccessExpression accessExpressions = accessExpressionCommand.getByOperation(operation);
        if (!StringUtils.isEmpty(accessExpressions.getOperation())
                && !StringUtils.isEmpty(accessExpressions.getExpression())) {
            expressions.put(accessExpressions.getOperation(), accessExpressions.getExpression());
        }
        if (expressions.isEmpty()) {
            expressions.putAll(defaultExpressions);
        }
    }

    private void putArgumentsInContext(List arguments, StandardEvaluationContext context) {
        for (Object argument : arguments) {
            final List pair = (List) argument;
            if (pair.isEmpty()) {
                continue;
            }
            String argname = pair.get(0).toString();
            Object value = pair.get(1);
            if (!StringUtils.isEmpty(argname)) {
                context.setVariable(argname, value);
            }
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        return true;
    }
}
