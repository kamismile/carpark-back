/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.service;

import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * Service for userInfo.
 *
 * @author Mirzoev_Nikita
 */
public interface UserInfoService {

    UserInfo getByLogin(String login);

    void deactivate(String login);

    UserInfo addUserInfo(UserInfo userInfo);

    void uppdateUserInfo(UserInfo userInfo);
}