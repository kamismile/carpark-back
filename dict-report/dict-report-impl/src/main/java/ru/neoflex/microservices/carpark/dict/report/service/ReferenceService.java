package ru.neoflex.microservices.carpark.dict.report.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.dict.report.model.ReferenceCommand;
import ru.neoflex.microservices.carpark.dict.report.repository.ReferenceRepository;

/**
 * @author vanosov
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
            repository.save(cmd.getEntity());
        } else if (Command.ADD.equals(cmd.getCommand())) {
            repository.save(cmd.getEntity());
        } else {
            repository.save(cmd.getOldEntity());
            repository.save(cmd.getEntity());
        }
    }
}