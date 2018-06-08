/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.auth.repository.UserInfoRepository;

/**
 * UserInfoService realization.
 *
 * @author mirzoevnik
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    /**
     * Repository for userInfo.
     *
     * @param userInfoRepository - repository for userInfo.
    */
    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * Autentification method.
     *
     * @param login login
     * @param password password
     * @return
    */
    @Override
    public UserInfo authenticateUserByLoginAndPassword(String login, String password) {
        return userInfoRepository.getByLoginAndPasswordAndActive(login, password, Boolean.TRUE);
    }


}
