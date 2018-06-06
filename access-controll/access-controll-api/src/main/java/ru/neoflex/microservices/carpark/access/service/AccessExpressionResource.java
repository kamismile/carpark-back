package ru.neoflex.microservices.carpark.access.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import ru.neoflex.microservices.carpark.access.model.AccessExpression;

import java.util.List;

/**
 * Interface for access controll expressions
 * @autor rmorenko.
 */
public interface AccessExpressionResource {

   @RequestMapping(method = RequestMethod.GET, value = "/expression")
   AccessExpression getByOperation(@RequestParam("operation")String operation);

   @RequestMapping(method = RequestMethod.GET, value = "/expression/{id}")
   AccessExpression get(@RequestPart("id")Long id);

   @RequestMapping(method = RequestMethod.GET, value = "/expressions")
   List<AccessExpression> getAll();

   @RequestMapping(method = RequestMethod.PUT, value = "/expression/add")
   AccessExpression add(AccessExpression expression);

   @RequestMapping(method = RequestMethod.DELETE, value = "/expression/delete")
   void delete(Long id);

   @RequestMapping(method = RequestMethod.PUT, value = "/expression/update")
   void update(AccessExpression expression);





}
