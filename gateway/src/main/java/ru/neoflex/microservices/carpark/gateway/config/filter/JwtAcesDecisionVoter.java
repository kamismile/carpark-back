package ru.neoflex.microservices.carpark.gateway.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * @author rmorenko
 */
public class JwtAcesDecisionVoter implements AccessDecisionVoter {

    private static final Logger log = LoggerFactory.getLogger(JwtAcesDecisionVoter.class);

    public JwtAcesDecisionVoter() {
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class clazz) {
        return true;
    }

    public int vote(Authentication authentication, Object object, Collection collection) {
        if (object instanceof FilterInvocation
                && ((FilterInvocation) object).getRequestUrl().contains("auth/ui/tokens")) {
            return ACCESS_GRANTED;
        }
        if (object instanceof FilterInvocation
                && ((FilterInvocation) object).getRequestUrl().contains("hystrix")) {
            return ACCESS_GRANTED;
        }
        if (object instanceof FilterInvocation
                && ((FilterInvocation) object).getRequestUrl().contains("swagger")) {
            return ACCESS_GRANTED;
        }
        if (object instanceof FilterInvocation
                && ((FilterInvocation) object).getHttpRequest().getMethod().equals("OPTIONS")) {
            return ACCESS_GRANTED;
        }

        if (authentication.getAuthorities() == null || authentication.getAuthorities().isEmpty()) {
            return ACCESS_DENIED;
        }
        if (!(authentication.getDetails() instanceof OAuth2AuthenticationDetails)){
            return ACCESS_DENIED;
        }
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)authentication.getDetails();
        Object details = oAuth2AuthenticationDetails.getDecodedDetails();
        if (details == null) {
            return ACCESS_DENIED;
        }
        return ACCESS_GRANTED;
    }

}

