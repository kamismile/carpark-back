package ru.neoflex.microservices.carpark.auth.access;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import ru.neoflex.microservices.carpark.auth.dto.AuthRequestParams;
import ru.neoflex.microservices.carpark.auth.helper.HttpRequestHelper;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;
import ru.neoflex.microservices.carpark.auth.service.UserInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Token granter
 * @author rmorenko.
 */
@Slf4j
public class LoginPasswordTokenGranter extends AbstractTokenGranter {

        public static final String GRANT_TYPE = "login_password";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String REDIRECT_URI = "redirect_uri";
        public static final String AUTH_REQUEST_PARAMS = "auth_request_params";
        private HttpRequestHelper httpRequestHelper;
        private UserInfoService userInfoService;


        public LoginPasswordTokenGranter(AuthorizationServerTokenServices tokenServices,
                ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,
                HttpRequestHelper httpRequestHelper, UserInfoService userInfoService) {
                super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
                this.userInfoService = userInfoService;
                this.httpRequestHelper = httpRequestHelper;
        }

        @Override
        protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
                AuthRequestParams authParameters = fillAuthRequestParams(tokenRequest);
                httpRequestHelper.setRequestAttribute(AUTH_REQUEST_PARAMS, authParameters);
                UserInfo user = userInfoService.authenticateUserByLoginAndPassword(authParameters.getLogin(), authParameters.getPassword());
                List<GrantedAuthority> authorities = Stream.of((GrantedAuthority) () -> user.getRole()).collect(Collectors.toList());
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getLogin(),
                        user.getPassword(), authorities);
                OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(tokenRequest.createOAuth2Request(client), authentication);
                oAuth2Authentication.setDetails(user.getLocationId());
                SecurityContextHolder.getContext().setAuthentication(oAuth2Authentication);
                return oAuth2Authentication;
        }

        private AuthRequestParams fillAuthRequestParams(TokenRequest tokenRequest) {
                Map<String, String> requestParams = tokenRequest.getRequestParameters();
                AuthRequestParams params = new AuthRequestParams();
                params.setLogin(requestParams.get(LOGIN));
                params.setPassword(requestParams.get(PASSWORD));
                params.setRedirectUri(requestParams.get(REDIRECT_URI));
                params.setGrantType(tokenRequest.getGrantType());
                log.debug("Auth request params  for grant type{} was filled {}", GRANT_TYPE, params);
                return params;
        }
}
