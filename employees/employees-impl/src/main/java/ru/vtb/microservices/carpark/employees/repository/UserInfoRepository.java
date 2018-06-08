/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * Reporsitory for UserInfo.
 *
 * @author Mirzoev_Nikita
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo getByLogin(String login);
}
