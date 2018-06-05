package ru.neoflex.microservices.carpark.auth.service;

import ru.neoflex.microservices.carpark.auth.model.UserInfo;

/**
 * @author mirzoevnik
 */
public interface UserInfoService {

    UserInfo getByLogin(String login);

    UserInfo authenticateUserByLoginAndPassword(String login, String password);

    void deactivate(String login);

    void addUserInfo(UserInfo userInfo);

    void uppdateUserInfo(UserInfo userInfo);
}
