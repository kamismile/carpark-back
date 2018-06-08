/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */


package ru.vtb.microservices.carpark.auth.helper;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

/**
 * Provides functionality for working with {@link HttpServletRequest}
 * and {@link HttpServletResponse}.
 *
 * @author Roman_Morenko
 */
@Component
@Getter
public class HttpRequestHelper {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;

    public void setRequestAttribute(String key, Object value) {
        httpServletRequest.setAttribute(key, value);
    }

    public Cookie getCookieFromCurrentRequest(String key) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (!Objects.isNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void removeCookies(HttpServletResponse response, Cookie... cookies) {
        Arrays.stream(cookies).filter(Objects::nonNull).forEach(cookie -> {
            cookie.setValue(StringUtils.EMPTY);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        });
    }

}
