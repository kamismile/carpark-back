package ru.neoflex.microservices.carpark.cars.model;

import static ru.neoflex.microservices.carpark.commons.util.ResolverUtils.getDateParameter;
import static ru.neoflex.microservices.carpark.commons.util.ResolverUtils.getIntegerParameter;

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
 * Resolver for CarFilter
 *
 * @author rmorenko
 */
public class CarFilterResolver implements HandlerMethodArgumentResolver {
        @Override public boolean supportsParameter(MethodParameter parameter) {
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
                return carFilter;
        }
}

/*
 private List<String> marks;

        private Integer yearFrom;

        private Integer yearTo;

        private Long currentLocationId;

        private Double mileageFrom;

        private Double mileageTo;

        private Long locationId;

        private List<String> statuses;
 */