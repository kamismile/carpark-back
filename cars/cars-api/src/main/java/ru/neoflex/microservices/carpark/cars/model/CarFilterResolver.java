package ru.neoflex.microservices.carpark.cars.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.neoflex.microservices.carpark.commons.util.ResolverUtils.*;

/**
 * Resolver for CarFilter
 *
 * @author rmorenko
 */
public class CarFilterResolver implements HandlerMethodArgumentResolver {

        private ObjectMapper objectMapper;

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
                return CarFilter.class.isAssignableFrom(parameter.getParameterType());
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                CarFilter carFilter = new CarFilter();
                List<String> marks =
                        Stream.of(Optional.ofNullable(webRequest.getParameter("marks"))
                                .orElse("").split(","))
                                .collect(Collectors.toList());
                carFilter.setMarks(marks);
                carFilter.setYearFrom(getIntegerParameter(webRequest, "yearFrom"));
                carFilter.setYearTo(getIntegerParameter(webRequest,"yearTo"));
                carFilter.setCurrentLocationId(getLongParameter(webRequest,"currentLocationId"));
                carFilter.setLocationId(getLongParameter(webRequest,"locationId"));
                carFilter.setMileageFrom(getDoubleParameter(webRequest,"mileageFrom"));
                carFilter.setMileageTo(getDoubleParameter(webRequest,"mileageTo"));
                List<String> currentStatuses =
                        Stream.of(Optional.ofNullable(webRequest.getParameter("currentStatuses"))
                                .orElse("").split(","))
                                .collect(Collectors.toList());
                carFilter.setCurrentStatuses(currentStatuses);
                return carFilter;
        }

        public ObjectMapper getObjectMapper() {
                return objectMapper;
        }

        public void setObjectMapper(ObjectMapper objectMapper) {
                this.objectMapper = objectMapper;
        }
}

