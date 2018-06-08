/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.auth.service;

import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * Service for userInfo.
 *
 * @author Nikita_Mirzoev
 */
@FunctionalInterface
public interface UserInfoService {

    UserInfo authenticateUserByLoginAndPassword(String login, String password);
}
