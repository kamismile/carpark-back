package ru.neoflex.microservices.carpark.employees.model;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Date;

/**
 * @author rmorenko
 */
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
        if (StringUtils.isEmpty(nativeWebRequest.getParameter(parameterName))){
            return null;
        }
        Long result;
        try {
            result =  Long.valueOf(nativeWebRequest.getParameter(parameterName));
        } catch (NumberFormatException ex){
            return  null;
        }
        return  new Date(result);
    }

}
