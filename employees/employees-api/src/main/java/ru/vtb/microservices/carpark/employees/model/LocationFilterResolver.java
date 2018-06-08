package ru.vtb.microservices.carpark.employees.model;

import static ru.vtb.microservices.carpark.commons.util.ResolverUtils.getBooleanParameter;

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
 * Resolver for locationFilter parameter.
 *
 * @author rmorenko
 */
public class LocationFilterResolver implements HandlerMethodArgumentResolver {

    private ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return LocationFilter.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        LocationFilter locationFilter = new LocationFilter();
        locationFilter.setActive(getBooleanParameter(webRequest, "active"));
        locationFilter.setAddress(webRequest.getParameter("address"));
        List<String> locationTypess =
                Stream.of(Optional.ofNullable(webRequest.getParameter("locationTypes"))
                        .orElse("").split(","))
                        .collect(Collectors.toList());
        locationFilter.setLocationTypes(locationTypess);
        return  locationFilter;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
