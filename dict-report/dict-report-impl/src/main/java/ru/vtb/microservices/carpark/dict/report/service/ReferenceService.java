/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dict.report.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.dict.report.model.ReferenceCommand;
import ru.vtb.microservices.carpark.dict.report.repository.ReferenceRepository;

/**
 * Service for reference.
 *
 * @author Vadim_Anosov
 */
@Service
@Data
@Transactional
public class ReferenceService {

    @Autowired
    ReferenceRepository repository;

    public void save(ReferenceCommand cmd) {
        if (Command.DELETE.equals(cmd.getCommand())) {
            cmd.getEntity().setActive(false);
        }
        repository.save(cmd.getEntity());
    }
}