/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.access.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.access.model.AccessExpression;
import ru.neoflex.microservices.carpark.access.repository.AccessExpressionRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for expression.
 *
 * @author Roman_Morenko
 */
@Service
@Getter
@Setter
@Transactional
public class AccessExpressionService {

    @Autowired
    private AccessExpressionRepository repository;

    public AccessExpression getByOperation(String operation) {
        return repository.findByOperation(operation).stream()
                       .findFirst().orElse(new AccessExpression());
    }

    public List<AccessExpression> getAll() {
        return repository.findAll();
    }

    public AccessExpression add(AccessExpression expression) {
        return repository.save(expression);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public void update(AccessExpression expression) {
        repository.save(expression);
    }

    public AccessExpression get(Long id) {
        return repository.findOne(id);
    }
}
