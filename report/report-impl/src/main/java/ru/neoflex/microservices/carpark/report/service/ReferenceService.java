package ru.neoflex.microservices.carpark.report.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.report.model.ReferenceCommand;
import ru.neoflex.microservices.carpark.report.repository.ReferenceRepository;

/**
 * @author rmorenko
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
                } else {
                        repository.save(cmd.getEntity());
                }
        }
}
