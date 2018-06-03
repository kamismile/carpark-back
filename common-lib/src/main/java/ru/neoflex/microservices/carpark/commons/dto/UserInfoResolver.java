package ru.neoflex.microservices.carpark.commons.dto;

;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.neoflex.microservices.carpark.commons.util.UserInfoUtil;

import java.util.Collection;
import java.util.Map;


/**
 * @author rmorenko
 */
public class UserInfoResolver implements HandlerMethodArgumentResolver {

        private ObjectMapper objectMapper;


        @Override
        public boolean supportsParameter(MethodParameter parameter) {
                return UserInfo.class.isAssignableFrom(parameter.getParameterType());
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return UserInfoUtil.getInstance(authentication);


        }

        public ObjectMapper getObjectMapper() {
                return objectMapper;
        }

        public void setObjectMapper(ObjectMapper objectMapper) {
                this.objectMapper = objectMapper;
        }
}
