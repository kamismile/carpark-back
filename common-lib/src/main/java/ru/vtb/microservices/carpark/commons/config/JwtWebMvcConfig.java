/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.vtb.microservices.carpark.commons.dto.PageResolver;
import ru.vtb.microservices.carpark.commons.dto.UserInfoResolver;

import java.util.List;

/**
 * Jwt Config.
 *
 * @author Roman_Morenko
 */
public class JwtWebMvcConfig extends WebMvcConfigurerAdapter {

    public JwtWebMvcConfig() {
        super();
    }

    @Bean
    public UserInfoResolver userInfoResolver() {
        UserInfoResolver userInfoResolver = new UserInfoResolver();
        userInfoResolver.setObjectMapper(new ObjectMapper());
        return  userInfoResolver;
    }

    @Bean
    public PageResolver pageResolver() {
        PageResolver pageResolver = new PageResolver();
        pageResolver.setObjectMapper(new ObjectMapper());
        return pageResolver;
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.userInfoResolver());
        argumentResolvers.add(this.pageResolver());
    }
}
