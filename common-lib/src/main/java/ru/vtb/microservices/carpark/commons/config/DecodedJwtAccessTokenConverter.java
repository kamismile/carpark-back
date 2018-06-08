package ru.vtb.microservices.carpark.commons.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * @author rmorenko
 */
public class DecodedJwtAccessTokenConverter extends JwtAccessTokenConverter {
    public DecodedJwtAccessTokenConverter() {
        super();
    }

    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        authentication.setDetails(map);
        return authentication;
    }
}

