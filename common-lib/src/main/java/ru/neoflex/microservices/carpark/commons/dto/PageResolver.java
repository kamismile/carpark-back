package ru.neoflex.microservices.carpark.commons.dto;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

/**
 * @rmorenko.
 */
public class PageResolver implements HandlerMethodArgumentResolver {

    public static final String PAGE_DEFAULT = "0";
    public static final String COUNT_DEFAULT = "10";
    public static final String SORT_DEFAULT = Sort.Direction.ASC.toString();
    private ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Objects.equals(methodParameter.getParameterType(), PageRequest.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String page  = nativeWebRequest.getParameter("page");
        String count = nativeWebRequest.getParameter("count");
        String order = nativeWebRequest.getParameter("order");
        String sortColumns = nativeWebRequest.getParameter("sort");
        page = Optional.ofNullable(page).orElse(PAGE_DEFAULT);
        count = Optional.ofNullable(count).orElse(COUNT_DEFAULT);
        order = Optional.ofNullable(order).orElse(SORT_DEFAULT);
        if (order.toUpperCase() != SORT_DEFAULT
                && order.toUpperCase() != Sort.Direction.DESC.toString() ){
            order = SORT_DEFAULT;
        }
        if (StringUtils.isEmpty(sortColumns)){
            return new PageRequest(Integer.valueOf(page), Integer.valueOf(count));
        }
        return new PageRequest(Integer.valueOf(page), Integer.valueOf(count),
                Sort.Direction.valueOf(order), sortColumns.split(","));
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
