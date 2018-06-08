package ru.vtb.microservices.carpark.employees.service;

import ru.vtb.microservices.carpark.auth.model.UserInfo;

public interface UserInfoService {

    UserInfo getByLogin(String login);

    void deactivate(String login);

    UserInfo addUserInfo(UserInfo userInfo);

    void uppdateUserInfo(UserInfo userInfo);
}