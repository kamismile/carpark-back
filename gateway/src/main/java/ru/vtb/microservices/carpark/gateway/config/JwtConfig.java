package ru.vtb.microservices.carpark.gateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.apache.commons.io.IOUtils;
import ru.vtb.microservices.carpark.commons.config.DecodedJwtAccessTokenConverter;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 *  @author rmorenko
 */
@Configuration
public class JwtConfig {

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.tokenStore());
        return defaultTokenServices;
    }

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new DecodedJwtAccessTokenConverter();
        ClassPathResource resource = new ClassPathResource("test.openssl");
        String publicKey = null;

        try {
            publicKey = IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        converter.setVerifierKey(publicKey);
        return converter;
    }

}
