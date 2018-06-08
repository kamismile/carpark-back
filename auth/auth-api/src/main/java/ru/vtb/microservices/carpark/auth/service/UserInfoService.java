package ru.vtb.microservices.carpark.auth.service;

import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * @author mirzoevnik
 */
public interface UserInfoService {

    UserInfo authenticateUserByLoginAndPassword(String login, String password);
}
