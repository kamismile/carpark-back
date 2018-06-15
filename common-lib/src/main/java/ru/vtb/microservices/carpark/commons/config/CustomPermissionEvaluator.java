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
 * CustomPermissionEvaluator for acl.
 *
 * @author Roman_Morenko
 */
@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {

    public static final String USER_INFO_VAR = "userInfo";
    public static final String FILTER_SUFF = "_filter";
    public static final String TARGET = "target";
    private static final Map<String, String> EXPRESSIONS;
    private static final Map<String, String> DEFAULT_EXPRESSIONS;

    @Autowired
    private AccessExpressionCommand accessExpressionCommand;

    public static final String USER_INFO_ROLE_ADMINISTRATOR1 = "#userInfo.role == 'administrator'";
    public static final String USER_INFO_ROLE_ADMINISTRATOR = USER_INFO_ROLE_ADMINISTRATOR1;

    static {
        EXPRESSIONS = new ConcurrentHashMap<>();
        DEFAULT_EXPRESSIONS = new ConcurrentHashMap<>();
        DEFAULT_EXPRESSIONS.put("getCars_filter",
                " ( #userInfo.role == 'rental_manager' && #userInfo.locationId == #target.currentLocationId ) "
                        + " || ( #userInfo.role == 'service_manager' && "
                        + " ( #target.currentStatus == 'in_service' ||  #target.nextStatus == 'in_service' )) "
                        + " || #userInfo.role == 'management' ||  #userInfo.role == 'administrator' ");
        DEFAULT_EXPRESSIONS.put("getReferencesByRubric_filter", "#userInfo.role != 'test'");
        DEFAULT_EXPRESSIONS.put("changeCarState", "#userInfo.role == 'administrator' || #userInfo.role == 'management' "
                + "|| ( #userInfo.role == 'rental_manager' "
                + "&&  ( #stringEvent == 'RENT' || #stringEvent == 'RETURN' ) )|| ( #userInfo.role == 'service_manager' "
                + "&&  #stringEvent == 'SERVICE')");
        DEFAULT_EXPRESSIONS.put("deleteCar", USER_INFO_ROLE_ADMINISTRATOR1);
        DEFAULT_EXPRESSIONS.put("createCar", USER_INFO_ROLE_ADMINISTRATOR1);
        DEFAULT_EXPRESSIONS.put("updateCar", USER_INFO_ROLE_ADMINISTRATOR);
        DEFAULT_EXPRESSIONS.put("createPreorder", "#userInfo.role == 'administrator' || #userInfo.role == 'rental_manager'");

    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject,
                                 Object permission) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        String method = ((List) permission).get(0).toString();
        UserInfo userInfo = UserInfoUtil.getNewInstance(authentication);
        context.setVariable(USER_INFO_VAR, userInfo);
        if (!method.contains(FILTER_SUFF)) {
            putArgumentsInContext((List) targetDomainObject, context);
        } else {
            context.setVariable(TARGET, targetDomainObject);
            List arguments = ((List) permission).subList(1, ((List) permission).size());
            putArgumentsInContext(arguments, context);
        }
        initExpressionsIfNeedForDemo(method);
        String expression = EXPRESSIONS.get(method);
        if (!StringUtils.isEmpty(expression)) {
            return (Boolean) parser.parseExpression(expression).getValue(context);
        }
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        return true;
    }

    protected void initExpressionsIfNeed() {
        List<AccessExpression> accessExpressions = accessExpressionCommand.getAll();
        accessExpressions.stream()
                .filter(e -> !StringUtils.isEmpty(e.getOperation())
                        && !StringUtils.isEmpty(e.getExpression()))
                .forEach(e -> EXPRESSIONS.put(e.getOperation(), e.getExpression()));
        if (EXPRESSIONS.isEmpty()) {
            EXPRESSIONS.putAll(DEFAULT_EXPRESSIONS);
        }
    }

    //TODO: special for demonstration Hystrix-Turbine
    private void initExpressionsIfNeedForDemo(String operation) {
        AccessExpression accessExpressions = accessExpressionCommand.getByOperation(operation);
        if (!StringUtils.isEmpty(accessExpressions.getOperation())
                && !StringUtils.isEmpty(accessExpressions.getExpression())) {
            EXPRESSIONS.put(accessExpressions.getOperation(), accessExpressions.getExpression());
        }
        if (EXPRESSIONS.isEmpty()) {
            EXPRESSIONS.putAll(DEFAULT_EXPRESSIONS);
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


}
