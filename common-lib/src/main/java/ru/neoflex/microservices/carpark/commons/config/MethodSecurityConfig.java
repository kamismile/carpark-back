package ru.neoflex.microservices.carpark.commons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import ru.neoflex.microservices.carpark.access.feign.AccessExpressionFeign;
import ru.neoflex.microservices.carpark.commons.command.AccessExpressionCommand;

/**
 * @author rmorenko
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Autowired
    private AccessExpressionFeign accessExpressionFeign;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler =
            new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(customPermissionEvaluator);
        return expressionHandler;
    }

    @Bean
    public AccessExpressionCommand accessExpressionCommand() {
        return new AccessExpressionCommand();
    }

    @Bean
    public CustomPermissionEvaluator customPermissionEvaluator(){
        return new CustomPermissionEvaluator();
    }

}
