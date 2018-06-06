package ru.neoflex.microservices.carpark.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.employees.api.UserInfoApi;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;
import ru.neoflex.microservices.carpark.employees.service.UserInfoService;

/**
 * @author mirzoevnik
 */
@RestController
public class UserInfoController implements UserInfoApi {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    @GetMapping(value = "/user/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo getByLogin(@PathVariable String login) {
        return userInfoService.getByLogin(login);
    }

    @Override
    @DeleteMapping(value = "/deactivate/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deactivate(@PathVariable String login) {
        userInfoService.deactivate(login);
    }

    @Override
    @PutMapping(value = "/user/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo addUserInfo(@RequestBody UserInfo userInfo) {
      return userInfoService.addUserInfo(userInfo);
    }

    @Override
    @PutMapping(value = "/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserInfo(@RequestBody UserInfo userInfo) {
      userInfoService.uppdateUserInfo(userInfo);
    }
}
