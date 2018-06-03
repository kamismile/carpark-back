package ru.neoflex.microservices.carpark.dicts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.ReferenceCommand;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.repository.ReferenceRepository;
import ru.neoflex.microservices.carpark.dicts.sender.DictSender;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceService;

import java.util.List;

/**
 * @author mirzoevnik
 */
@Service
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
        referenceCommand.setCommand(Command.UPDATE);
        referenceCommand.setEntity(reference);
        referenceCommand.setOldEntity(oldReference);
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