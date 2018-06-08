/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.dicts.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.model.ReferenceCommand;
import ru.vtb.microservices.carpark.dicts.repository.ReferenceRepository;
import ru.vtb.microservices.carpark.dicts.sender.DictSender;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.repository.ReferenceRepository;

import java.util.List;

/**
 * Implementation for Reference service.
 *
 * @author Mirzoev_Nikita
 */
@Service
@Data
@Transactional
public class ReferenceServiceImpl implements ReferenceService {

    private final ReferenceRepository referenceRepository;

    private final DictSender sender;

    @Value("${kafka.topic.reference}")
    String referenceTopic;

    @Autowired
    public ReferenceServiceImpl(ReferenceRepository referenceRepository, DictSender sender) {
        this.referenceRepository = referenceRepository;
        this.sender = sender;
    }

    @HystrixCommand(threadPoolKey = "findDictByRubric", commandKey = "DictExpressionCommand")
    public List<Reference> findByRubric(Rubric rubric) {
        return referenceRepository.findByRubric(rubric);
    }

    @Override
    public void createReference(Reference reference) {
        referenceRepository.save(reference);
        ReferenceCommand referenceCommand = new ReferenceCommand();
        referenceCommand.setCommand(Command.ADD);
        referenceCommand.setEntity(reference);
        sender.send(referenceTopic, referenceCommand);
    }

    @Override
    public void updateReference(Reference reference) {
        Reference oldReference = referenceRepository.findOne(reference.getCode());
        referenceRepository.save(reference);
        ReferenceCommand referenceCommand = new ReferenceCommand();
        referenceCommand.setOldEntity(oldReference);
        referenceCommand.setCommand(Command.UPDATE);
        referenceCommand.setEntity(reference);
        sender.send(referenceTopic, referenceCommand);
    }

    @Override
    public void disableReference(String code) {
        Reference reference = referenceRepository.findByCode(code);
        reference.setActive(false);
        referenceRepository.save(reference);
        ReferenceCommand referenceCommand = new ReferenceCommand();
        referenceCommand.setCommand(Command.DELETE);
        referenceCommand.setEntity(reference);
        sender.send(referenceTopic, referenceCommand);
    }
}