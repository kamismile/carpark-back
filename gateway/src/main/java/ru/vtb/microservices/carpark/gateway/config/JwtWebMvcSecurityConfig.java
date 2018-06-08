package ru.vtb.microservices.carpark.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import ru.vtb.microservices.carpark.gateway.config.filter.JwtAcesDecisionVoter;

import java.util.List;

/**
 * @author rmorenko
 */
@Configuration
public class JwtWebMvcSecurityConfig extends ResourceServerConfigurerAdapter {
        @Autowired
        private List<AccessDecisionVoter<? extends Object>> decisionVoters;

        public JwtWebMvcSecurityConfig() {
                super();
        }

        public void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().accessDecisionManager(this.accessDecisionManager());
        }

        @Bean
        public AccessDecisionManager accessDecisionManager() {
               // WebExpressionVoter expressionVoter = new WebExpressionVoter();
               //expressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
               return new UnanimousBased(this.decisionVoters);
        }

        @Bean
        public AccessDecisionVoter accessDecisionVoter() {
                return new JwtAcesDecisionVoter();
        }
}
