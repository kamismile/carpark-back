package ru.neoflex.microservices.carpark.employees.model;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Resolver for page parameter.
 *
 * @author rmorenko
 */
public class PageResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return PageRequest.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Integer page = Integer.valueOf(nativeWebRequest.getParameter("page"));
        Integer count = Integer.valueOf(nativeWebRequest.getParameter("count"));
        PageRequest pageRequest = new PageRequest(page, count);
        String sort =  nativeWebRequest.getParameter("sort");
        String sortColumn =  nativeWebRequest.getParameter("sortColumn");
        if (StringUtils.isEmpty(sort) && StringUtils.isEmpty(sortColumn)) {
            return pageRequest;
        }
        return  new PageRequest(page, count, Sort.Direction.valueOf(sort), sortColumn);

    }
}
