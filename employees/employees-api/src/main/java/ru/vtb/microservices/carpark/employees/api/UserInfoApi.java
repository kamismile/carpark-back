/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.api;

import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * Api for UdserInfo.
 *
 * @author Nikita_Morezoev
 */
public interface UserInfoApi {

    UserInfo getByLogin(String login);

    void deactivate(String login);

    UserInfo addUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);
}
