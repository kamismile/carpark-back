package ru.vtb.microservices.carpark.commons.config;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

/**
 * @author rmorenko
 */
@Configuration
@ConditionalOnProperty(value = "feign.oauth2.enabled", matchIfMissing = true)
public class OAuth2FeignAutoConfiguration {

        @Bean
        public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
                return new OAuth2FeignRequestInterceptor(oauth2ClientContext);
        }
}
