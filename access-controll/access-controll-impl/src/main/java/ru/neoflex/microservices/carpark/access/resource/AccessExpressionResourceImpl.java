package ru.neoflex.microservices.carpark.access.resource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.access.model.AccessExpresion;
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
   public AccessExpresion getByOperation(String operation) {
      return accessExpressionService.getByOperation(operation);
   }

   @Override
   public AccessExpresion get(@RequestPart("id") Long id) {
      return null;
   }

   @Override
   public List<AccessExpresion> getAll() {
      return accessExpressionService.getAll();
   }

   @Override
   public AccessExpresion add(AccessExpresion expresion) {
     return  accessExpressionService.add(expresion);
   }

   @Override
   public void delete(Long id) {
      accessExpressionService.delete(id);
   }

   @Override
   public void update(AccessExpresion expresion) {
      accessExpressionService.update(expresion);
   }
}
