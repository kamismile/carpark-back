package ru.vtb.microservices.carpark.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.employees.repository.UserInfoRepository;
import ru.vtb.microservices.carpark.employees.service.UserInfoService;

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
    public UserInfo getByLogin(String login) {
        return userInfoRepository.getByLogin(login);
    }


    @Override
    public void deactivate(String login) {
        UserInfo userInfo = userInfoRepository.getByLogin(login);
        userInfo.setActive(false);
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo addUserInfo(UserInfo userInfo) {
       return save(userInfo);
    }

    private UserInfo save(UserInfo userInfo) {
       return userInfoRepository.save(userInfo);
    }

    @Override
    public void uppdateUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

}
