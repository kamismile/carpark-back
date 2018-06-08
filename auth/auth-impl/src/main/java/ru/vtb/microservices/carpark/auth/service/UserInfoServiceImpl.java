package ru.vtb.microservices.carpark.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.auth.repository.UserInfoRepository;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.auth.repository.UserInfoRepository;

/**
 * @author mirzoevnik
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo authenticateUserByLoginAndPassword(String login, String password) {
        return userInfoRepository.getByLoginAndPasswordAndActive(login, password, Boolean.TRUE);
    }


}