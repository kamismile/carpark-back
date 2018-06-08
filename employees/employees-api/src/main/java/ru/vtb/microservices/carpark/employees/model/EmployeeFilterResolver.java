/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.model;

import static ru.vtb.microservices.carpark.commons.util.ResolverUtils.getBooleanParameter;
import static ru.vtb.microservices.carpark.commons.util.ResolverUtils.getDateParameter;
import static ru.vtb.microservices.carpark.commons.util.ResolverUtils.getLongParameter;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FilterResolver for employee parameter.
 *
 * @author Roman_Morenko
 */
public class EmployeeFilterResolver implements HandlerMethodArgumentResolver {

    private ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return EmployeeFilter.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        EmployeeFilter employeeFilter = new EmployeeFilter();
        employeeFilter.setName(nativeWebRequest.getParameter("name"));
        employeeFilter.setSurname(nativeWebRequest.getParameter("surname"));
        employeeFilter.setPatronymic(nativeWebRequest.getParameter("patronymic"));
        List<String> positions =
                Stream.of(Optional.ofNullable(nativeWebRequest.getParameter("positions"))
                        .orElse("").split(","))
                        .collect(Collectors.toList());
        employeeFilter.setPositions(positions);
        List<String> locations =
                Stream.of(Optional.ofNullable(nativeWebRequest.getParameter("locations"))
                        .orElse("").split(","))
                        .collect(Collectors.toList());
        employeeFilter.setLocations(locations);
        employeeFilter.setAppointmentDateFrom(getDateParameter(nativeWebRequest, "appointmentDateFrom"));
        employeeFilter.setAppointmentDateTo(getDateParameter(nativeWebRequest, "appointmentDateFrom"));
        employeeFilter.setUserId(getLongParameter(nativeWebRequest,"userId"));
        employeeFilter.setActive(getBooleanParameter(nativeWebRequest,"active"));
        return employeeFilter;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}