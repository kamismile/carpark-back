/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import ru.vtb.microservices.carpark.auth.service.UserInfoService;
import ru.vtb.microservices.carpark.auth.service.UserInfoService;


/**
 * Authentication manager  configuration.
 *
 * @author Roman_Morenko
 */
@Configuration
public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserInfoService userService;


}
