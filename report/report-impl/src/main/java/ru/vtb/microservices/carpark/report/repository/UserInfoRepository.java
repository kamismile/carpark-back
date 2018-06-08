/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.microservices.carpark.auth.model.UserInfo;

/**
 * Repository for UserInfo.
 *
 * @author  Roman_Morenko
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByLogin( String login );
}
