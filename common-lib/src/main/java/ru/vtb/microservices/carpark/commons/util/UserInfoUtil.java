/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.StringUtils;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;

import java.util.Collection;
import java.util.Map;

/**
 * UserInfoUtil.
 *
 * @author  Roman_Morenko
 */
@Slf4j
public class UserInfoUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(UserInfoUtil.class);

    private UserInfoUtil() {

    }

    public static UserInfo getInstance(Authentication authentication) {
        Object details = authentication.getDetails();
        Map map = (Map) ((OAuth2AuthenticationDetails) details).getDecodedDetails();
        String userName = authentication.getPrincipal().toString();
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = getRole(authorities);
        Long locationId;
        try {
            locationId = Long.valueOf(map.get("locationId").toString());
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            return new UserInfo(userName, role, null);
        }
        return new UserInfo(userName, role, locationId);
    }

    private static String getRole(Collection<? extends GrantedAuthority> authorities) {
        String role = "";
        if (StringUtils.isEmpty(authorities.isEmpty())) {
            role = authorities.stream().map(GrantedAuthority::getAuthority).findFirst().orElse("");
        }
        return role;
    }
}
