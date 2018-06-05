package ru.neoflex.microservices.carpark.employees.model;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * @author rmorenko
 */
@Slf4j
public class ResolverUtils {

    private ResolverUtils(){
        super();
    }

    public static Boolean getBooleanParameter(NativeWebRequest nativeWebRequest, String parameterName) {

        if (StringUtils.isEmpty(nativeWebRequest.getParameter(parameterName))){
            return (Boolean) null;
        }
        return  Boolean.valueOf(nativeWebRequest.getParameter(parameterName));
    }

    public static Date getDateParameter(NativeWebRequest nativeWebRequest, String parameterName) {

        final String parameterValue = nativeWebRequest.getParameter(parameterName);
        if (StringUtils.isEmpty(parameterValue)){
            return null;
        }

        try {
            new Date(Long.valueOf(parameterValue));
        } catch (NumberFormatException ex){
         log.debug(ex.getMessage());
        }

        try {
            return new ISO8601DateFormat().parse(parameterName);
        } catch (ParseException e) {
            throw new DateTimeParseException(e.getMessage(), parameterValue, 0);
        }
    }

}
