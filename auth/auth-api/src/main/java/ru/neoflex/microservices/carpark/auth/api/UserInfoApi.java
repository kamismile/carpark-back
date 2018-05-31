package ru.neoflex.microservices.carpark.auth.api;

import ru.neoflex.microservices.carpark.auth.model.UserInfo;

/**
 * @author mirzoevnik
 */
public interface UserInfoApi {

    UserInfo getByLogin(String login);
}
