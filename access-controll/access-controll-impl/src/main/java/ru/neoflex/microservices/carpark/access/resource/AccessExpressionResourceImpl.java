package ru.neoflex.microservices.carpark.access.resource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.access.model.AccessExpression;
import ru.neoflex.microservices.carpark.access.service.AccessExpressionResource;
import ru.neoflex.microservices.carpark.access.service.AccessExpressionService;

import java.util.List;

/**
 * @author rmorenko
 */
@RestController
@Slf4j
@AllArgsConstructor
public class AccessExpressionResourceImpl implements AccessExpressionResource {

   private AccessExpressionService accessExpressionService;

   @RequestMapping(method = RequestMethod.GET, value = "/expression") @Override
   public AccessExpression getByOperation(String operation) {
      return accessExpressionService.getByOperation(operation);
   }

   @Override
   public AccessExpression get(@RequestPart("id") Long id) {
      return null;
   }

   @Override
   public List<AccessExpression> getAll() {
      return accessExpressionService.getAll();
   }

   @Override
   public AccessExpression add(AccessExpression expression) {
     return  accessExpressionService.add(expression);
   }

   @Override
   public void delete(Long id) {
      accessExpressionService.delete(id);
   }

   @Override
   public void update(AccessExpression expression) {
      accessExpressionService.update(expression);
   }
}
