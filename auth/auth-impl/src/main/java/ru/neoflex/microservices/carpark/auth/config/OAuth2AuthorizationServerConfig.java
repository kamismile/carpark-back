
package ru.neoflex.microservices.carpark.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import ru.neoflex.microservices.carpark.auth.access.LoginPasswordTokenGranter;
import ru.neoflex.microservices.carpark.auth.helper.HttpRequestHelper;
import ru.neoflex.microservices.carpark.auth.service.UserInfoService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * OAuth 2.0 authorization server configuration.
 *
 * @author rmorenko
 */
@Configuration
@ConfigurationProperties
@EnableAuthorizationServer
@Slf4j
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("jwtConverter")
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HttpRequestHelper httpRequestHelper;

    @Value("${security.session.duration}")
    private int accessTokenValiditySeconds;

    @Value("${security.oauth.client.scope}")
    private String[] scopes;

    @Value("${security.oauth.client.clientId}")
    private String clientId;

    @Value("${security.oauth.client.secret}")
    private String secret;

    @Value("${security.oauth.client.grantTypes}")
    private String[] grantTypes;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .tokenGranter(tokenGranter(endpoints))
                .tokenEnhancer(accessTokenConverter)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .pathMapping("/oauth/token", "/ui/tokens")
                .pathMapping("/oauth/token_key", "/ui/tokens/keys")
                .pathMapping("/oauth/authorize", "/ui/oauth/authorize");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .secret(secret)
                .authorizedGrantTypes(grantTypes)
                .autoApprove(true)
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .scopes(scopes);
    }

    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        final AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices();
        final ClientDetailsService clientDetailsService = endpoints.getClientDetailsService();
        final OAuth2RequestFactory oAuth2RequestFactory = endpoints.getOAuth2RequestFactory();
        return new LoginPasswordTokenGranter(tokenServices, clientDetailsService, oAuth2RequestFactory, httpRequestHelper, userInfoService);
    }

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore((JwtAccessTokenConverter) accessTokenConverter);
    }

    @Bean
    DefaultWebResponseExceptionTranslator getDefaultWebResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator();
    }

}
