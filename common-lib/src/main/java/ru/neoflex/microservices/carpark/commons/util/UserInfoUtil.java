package ru.neoflex.microservices.carpark.commons.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.StringUtils;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

import java.util.Collection;
import java.util.Map;

/**
 * Created by rmorenko on 29.05.2018.
 */
public class UserInfoUtil {

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
            return new UserInfo(userName, role, null);
        }
        return new UserInfo(userName, role, locationId);
    }

    private static String getRole(Collection<? extends GrantedAuthority> authorities) {
        String role = "";
        if (authorities != null || !authorities.isEmpty()) {
            role = authorities.stream().map(GrantedAuthority::getAuthority).findFirst().orElse("");
        }
        return role;
    }
}
