package ru.neoflex.microservices.carpark.employees.api;

import ru.neoflex.microservices.carpark.auth.model.UserInfo;

/**
 * @author mirzoevnik
 */
public interface UserInfoApi {

    UserInfo getByLogin(String login);

    void deactivate(String login);

    UserInfo addUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);
}
