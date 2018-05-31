package ru.neoflex.microservices.carpark.gateway.config.filter;

import com.jayway.jsonpath.JsonPath;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.neoflex.microservices.carpark.gateway.config.SecureCookieResponseWrapper;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Filters sets secure cookie response wrapper.
 *
 * @author rmorenko
 */
@Component
@Slf4j
public class SecureCookieFilter extends ZuulFilter {

    private static final String INTERNAL_SERVER_ERROR = "internal server error";
    private static final String INTERNAL_SERVER_ERROR_DESCRIPTION = "Zuul error";
    private static final String ERROR_KEY = "error";
    private static final String ERROR_DESCRIPTION_KEY = "error_description";

    @Override public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override public Object run() {
        try {
            doFilter();
        } catch (Exception ex) {
            log.warn("Exception occurred during filtering", ex);
            sendErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_DESCRIPTION);
        }
        return null;
    }


    protected void doFilter() {
        HttpServletResponse servletResponse = RequestContext.getCurrentContext().getResponse();
        SecureCookieResponseWrapper cookieWrapper = new SecureCookieResponseWrapper(servletResponse);
        RequestContext.getCurrentContext().setResponse(cookieWrapper);
    }

    protected void sendErrorResponse(HttpStatus httpStatus, String error, String errorDescription) {
        prepareErrorResponse(httpStatus);
        RequestContext.getCurrentContext().setResponseBody(getJsonErrorResponseBody(error, errorDescription));
    }

    private void prepareErrorResponse(HttpStatus httpStatus) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setResponseStatusCode(httpStatus.value());
        requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
        requestContext.setSendZuulResponse(false);
    }

    private String getJsonErrorResponseBody(String error, String errorDescription) {
        return JsonPath.parse(getErrorResponseMap(error, errorDescription)).jsonString();
    }

    private Map<String, Object> getErrorResponseMap(String error, String errorDescription) {
        Map<String, Object> errorResponseMap = new HashMap<>();
        errorResponseMap.put(ERROR_KEY, error);
        errorResponseMap.put(ERROR_DESCRIPTION_KEY, errorDescription);
        return errorResponseMap;
    }
}
