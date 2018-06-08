/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vtb.microservices.carpark.employees.api.UserInfoApi;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.employees.service.UserInfoService;

/**
 * api for userInfo.
 *
 * @author Mirzoev_Nikita
 */
@RestController
@Api(value = "userInfo", description = "Rest API for users operations", tags = "UserInfo API")
public class UserInfoController implements UserInfoApi {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    @GetMapping(value = "/user/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get user by login")
    public UserInfo getByLogin(@PathVariable String login) {
        return userInfoService.getByLogin(login);
    }

    @Override
    @DeleteMapping(value = "/deactivate/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deactivate user by login")
    public void deactivate(@PathVariable String login) {
        userInfoService.deactivate(login);
    }

    @Override
    @PutMapping(value = "/user/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new user")
    public UserInfo addUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.addUserInfo(userInfo);
    }

    @Override
    @PutMapping(value = "/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a user")
    public void updateUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.uppdateUserInfo(userInfo);
    }
}
