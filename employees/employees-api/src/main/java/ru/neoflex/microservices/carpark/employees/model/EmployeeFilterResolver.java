package ru.neoflex.microservices.carpark.employees.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.neoflex.microservices.carpark.employees.model.ResolverUtils.getBooleanParameter;
import static ru.neoflex.microservices.carpark.employees.model.ResolverUtils.getDateParameter;

/**
 * @author rmorenko
 */
public class EmployeeFilterResolver implements HandlerMethodArgumentResolver {

    private ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Employee.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
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
        employeeFilter.setUserId(nativeWebRequest.getParameter("useId"));
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