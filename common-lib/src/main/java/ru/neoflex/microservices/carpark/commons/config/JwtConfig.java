package ru.neoflex.microservices.carpark.commons.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author mirzoevnik
 */
@Configuration
public class JwtConfig {
    @Value("${security.oauth2.resource.jwt.keyValue}")
    private String verifierKey;

    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.tokenStore());
        return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        DecodedJwtAccessTokenConverter jwtAccessTokenConverter = new DecodedJwtAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey(this.verifierKey);
        return jwtAccessTokenConverter;
    }
}
