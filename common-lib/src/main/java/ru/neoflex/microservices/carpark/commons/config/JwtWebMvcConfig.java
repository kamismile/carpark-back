package ru.neoflex.microservices.carpark.commons.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.neoflex.microservices.carpark.commons.dto.UserInfoResolver;

import java.util.List;

/**
 * @author rmorenko
 */
@Configuration
public class JwtWebMvcConfig extends WebMvcConfigurerAdapter {
        public JwtWebMvcConfig() {
        }

        @Bean
        public UserInfoResolver userInfoResolver() {
                UserInfoResolver userInfoResolver = new UserInfoResolver();
                userInfoResolver.setObjectMapper(new ObjectMapper());
                return  userInfoResolver;
        }

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                argumentResolvers.add(this.userInfoResolver());
        }
}
