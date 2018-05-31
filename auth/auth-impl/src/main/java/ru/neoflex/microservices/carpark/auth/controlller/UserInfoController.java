package ru.neoflex.microservices.carpark.auth.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.auth.api.UserInfoApi;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;
import ru.neoflex.microservices.carpark.auth.service.UserInfoService;

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
}
