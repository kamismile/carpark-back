/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.util;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Util for resolver.
 *
 * @author Roman_Morenko
 */
@Slf4j
public class ResolverUtils {

    private ResolverUtils() {
        super();
    }

    public static Boolean getBooleanParameter(NativeWebRequest nativeWebRequest, String parameterName) {

        if (StringUtils.isEmpty(nativeWebRequest.getParameter(parameterName))) {
            return null;
        }
        return  Boolean.valueOf(nativeWebRequest.getParameter(parameterName));
    }

    /**
     * Util metod for resolve date parameter.
     * @param nativeWebRequest request instance
     * @param parameterName name of http request parameter
     * @return Date value of parameter
    */

    public static Date getDateParameter(NativeWebRequest nativeWebRequest, String parameterName) {

        final String parameterValue = nativeWebRequest.getParameter(parameterName);
        if (StringUtils.isEmpty(parameterValue)) {
            return null;
        }

        try {
            new Date(Long.parseLong(parameterValue));
        } catch (NumberFormatException ex) {
            log.debug(ex.getMessage());
        }

        try {
            return new ISO8601DateFormat().parse(parameterName);
        } catch (ParseException e) {
            throw new DateTimeParseException(e.getMessage(), parameterValue, 0, e);
        }
    }

    public static Integer getIntegerParameter(NativeWebRequest nativeWebRequest, String parameterName) {
        String stingVal = nativeWebRequest.getParameter(parameterName);
        if (StringUtils.isEmpty(stingVal)) {
            return null;
        }
        try {
            return Integer.parseInt(stingVal);
        } catch (NumberFormatException ex) {
            log.info(ex.getMessage());
            return null;
        }
    }

    public static Long getLongParameter(NativeWebRequest nativeWebRequest, String parameterName) {
        String stingVal = nativeWebRequest.getParameter(parameterName);
        if (StringUtils.isEmpty(stingVal)) {
            return null;
        }
        try {
            return Long.valueOf(stingVal);
        } catch (NumberFormatException ex) {
            log.info(ex.getMessage());
            return null;
        }
    }

    public static Double getDoubleParameter(NativeWebRequest nativeWebRequest, String parameterName) {
        String stingVal = nativeWebRequest.getParameter(parameterName);
        if (StringUtils.isEmpty(stingVal)) {
            return null;
        }
        try {
            return Double.valueOf(stingVal);
        } catch (NumberFormatException ex) {
            log.info(ex.getMessage());
            return null;
        }
    }
}
