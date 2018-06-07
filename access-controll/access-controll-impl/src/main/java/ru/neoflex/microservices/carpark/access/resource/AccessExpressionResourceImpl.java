/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.access.resource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.microservices.carpark.access.model.AccessExpression;
import ru.neoflex.microservices.carpark.access.service.AccessExpressionResource;
import ru.neoflex.microservices.carpark.access.service.AccessExpressionService;

import java.util.List;

/**
 * Service implementation for access controle.
 *
 * @author Roman_Morenko
 */
@RestController
@Slf4j
@AllArgsConstructor
public class AccessExpressionResourceImpl implements AccessExpressionResource {

    private AccessExpressionService accessExpressionService;

    @RequestMapping(method = RequestMethod.GET, value = "/expression")
    @Override
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
    public AccessExpression add(@RequestBody AccessExpression expression) {
        return  accessExpressionService.add(expression);
    }

    @Override
    public void delete(Long id) {
        accessExpressionService.delete(id);
    }

    @Override
    public void update(@RequestBody AccessExpression expression) {
        accessExpressionService.update(expression);
    }
}
