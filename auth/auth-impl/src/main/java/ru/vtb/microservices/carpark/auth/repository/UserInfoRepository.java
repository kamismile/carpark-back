/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * Repository for UserInfo.
 *
 * @author Nikkita_Mirzoev
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo getByLogin(String login);

    UserInfo getByLoginAndPassword(String login, String password);

    UserInfo getByLoginAndPasswordAndActive(String login, String password, Boolean active);
}
