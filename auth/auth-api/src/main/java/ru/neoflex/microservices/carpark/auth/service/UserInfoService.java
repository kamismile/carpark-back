package ru.neoflex.microservices.carpark.auth.service;

import ru.neoflex.microservices.carpark.auth.model.UserInfo;

/**
 * @author mirzoevnik
 */
public interface UserInfoService {

    UserInfo authenticateUserByLoginAndPassword(String login, String password);
}
