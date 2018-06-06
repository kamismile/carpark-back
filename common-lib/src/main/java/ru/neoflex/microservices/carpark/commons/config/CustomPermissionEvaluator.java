package ru.neoflex.microservices.carpark.commons.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import ru.neoflex.microservices.carpark.access.feign.AccessExpressionFeign;
import ru.neoflex.microservices.carpark.access.model.AccessExpression;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.util.UserInfoUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rmorenko
 */
@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {

        public static final String USER_INFO_VAR = "userInfo";
        public static final String FILTER_SUFF = "_filter";
        public static final String TARGET = "target";
        private static final Map<String, String> expressions;
        private static final Map<String, String> defaultExpressions;

        @Autowired
        private AccessExpressionFeign accessExpressionFeign;


    public static final String USER_INFO_ROLE_ADMINISTRATOR1 = "#userInfo.role == 'administrator'";
    public static final String USER_INFO_ROLE_ADMINISTRATOR = USER_INFO_ROLE_ADMINISTRATOR1;

    static {
           expressions= new ConcurrentHashMap<>();
           defaultExpressions = new ConcurrentHashMap<>();
           defaultExpressions.put("getCars_filter",
                  " ( #userInfo.role == 'rental_manager' && #userInfo.localityId == #target.currentLocationId ) "
                  + " || ( #userInfo.role == 'service_manager' && ( #target.currentStatus = 'in_service' ||  #target.nextStatus == 'in_service' )) "
                  + " || #userInfo.role == 'management' ||  #userInfo.role == 'administrator' ");
           defaultExpressions.put("getReferencesByRubric_filter", "#userInfo.role != 'test'");
           defaultExpressions.put("changeCarState", "#userInfo.role == 'management'");
           defaultExpressions.put("deleteCar", USER_INFO_ROLE_ADMINISTRATOR1);
           defaultExpressions.put("createCar", USER_INFO_ROLE_ADMINISTRATOR1);
           defaultExpressions.put("updateCar", USER_INFO_ROLE_ADMINISTRATOR);

        }


        @Override
        public boolean hasPermission(Authentication authentication, Object targetDomainObject,
                Object permission) {
                ExpressionParser parser = new SpelExpressionParser();
                StandardEvaluationContext context = new StandardEvaluationContext();
                String method = ((List)permission).get(0).toString();
                UserInfo userInfo = UserInfoUtil.getInstance(authentication);
                context.setVariable(USER_INFO_VAR, userInfo);
                if (!method.contains(FILTER_SUFF)){
                   putArgumentsInContext((List) targetDomainObject, context);
                } else {
                    context.setVariable(TARGET, targetDomainObject);
                    List arguments =  ((List)permission).subList(1, ((List)permission).size());
                    putArgumentsInContext( arguments, context);
                    //
                }
                initExpressionsIfNeed();
                String expression = expressions.get(method);
                if (!StringUtils.isEmpty(expression != null)){
                        return (Boolean)parser.parseExpression(expression).getValue(context);
                }
                return true;
        }

        private void initExpressionsIfNeed() {
                if(expressions.isEmpty()){
                  try {
                          List<AccessExpression> accessExpressions = accessExpressionFeign.getAll();
                          accessExpressions.stream()
                                  .filter(e -> !StringUtils.isEmpty(e.getOperation())
                                          && !StringUtils.isEmpty(e.getExpression()))
                                  .forEach(e -> expressions.put(e.getOperation(), e.getExpression()));
                          if (expressions.isEmpty()){
                                  expressions.putAll(defaultExpressions);
                          }
                  } catch (Exception ex){
                     log.info(ex.getMessage());
                     expressions.putAll(defaultExpressions);
                  }

                }
        }

        private void putArgumentsInContext(List arguments, StandardEvaluationContext context) {
                for (Object argument : arguments){
                    final List pair = (List) argument;
                    if (pair.isEmpty()){
                      continue;
                    }
                    String argname  =  pair.get(0).toString();
                    Object value =  pair.get(1);
                    if (!StringUtils.isEmpty(argname)) {
                            context.setVariable(argname, value);
                    }
                }
        }

        @Override
        public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                Object permission) {
                return true;
        }

}
