package ru.neoflex.microservices.carpark.access.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.neoflex.microservices.carpark.access.model.AccessExpresion;

import java.util.List;

/**
 * Interface for access controll expressions
 * @autor rmorenko.
 */
public interface AccessExpressionResource {
   @RequestMapping(method = RequestMethod.GET, value = "/expression")
   AccessExpresion getByOperation(@RequestParam("operation")String operation);
   @RequestMapping(method = RequestMethod.GET, value = "/expressions")
   List<AccessExpresion> getAll();
}
