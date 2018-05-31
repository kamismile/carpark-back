package ru.neoflex.microservices.carpark.gateway.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Http servlet response wrapper for set secure cookie.
 *
 * @author rmorenko
 */
public class SecureCookieResponseWrapper extends HttpServletResponseWrapper {
    public SecureCookieResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void addCookie(Cookie cookie) {
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        super.addCookie(cookie);
    }
}