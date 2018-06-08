/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 *  TokenConverter configuration.
 *
 * @author Roman_Morenko
 */
@Configuration
@Slf4j
public class TokenConverterConfig {

    public static final String LOCATION_ID = "locationId";

    /**
     * fabric method for  method JwtAccessTokenConverter.
     *
     * @return JwtAccessTokenConverter
    */
    @Order(1)
    @Bean("jwtConverter")
    public JwtAccessTokenConverter getTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                if (Objects.nonNull(accessToken.getValue()) && isUuid(accessToken.getValue())) {
                    accessToken = super.enhance(accessToken, authentication);
                }
                Map<String, Object> addInfo = new HashMap<>();
                addInfo.put(LOCATION_ID, authentication.getDetails());
                ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(addInfo);
                ((DefaultOAuth2AccessToken) accessToken).setValue(super.encode(accessToken, authentication));
                return  accessToken;

            }
        };
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("test.jks"), "test".toCharArray())
                .getKeyPair("test");
        converter.setKeyPair(keyPair);
        return converter;
    }

    public boolean isUuid(String uuidToCheck) {
        try {
            UUID uuid = UUID.fromString(uuidToCheck);
            log.debug("uuid" + uuid);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
