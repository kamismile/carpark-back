package ru.neoflex.microservices.carpark.report.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.report.model.EmployeeCommand;
import ru.neoflex.microservices.carpark.report.repository.EmployeeRepository;
import ru.neoflex.microservices.carpark.report.repository.LocationRepository;
import ru.neoflex.microservices.carpark.report.repository.UserInfoRepository;

/**
 * @author rmorenko
 */
@Service
@Slf4j
@Transactional
public class EmployeeService {

        @Autowired
        EmployeeRepository repository;

        @Autowired
        UserInfoRepository userInfoRepository;


        public void save(EmployeeCommand cmd) {
                if (Command.DELETE.equals(cmd.getCommand())) {
                        cmd.getEntity().setActive(false);
                        repository.save(cmd.getEntity());
                } else if (Command.ADD.equals(cmd.getCommand())) {
                        cmd.getEntity().setId(null);
                        Long userId = cmd.getEntity().getUser().getId();
                        if (userId == null) {
                           userInfoRepository.save(cmd.getEntity().getUser());
                        }

                } else {
                        Employee oldEmployee =  repository.findByUserId(cmd.getOldEntity().getUser().getId());
                        repository.delete(oldEmployee);
                        cmd.getEntity().setId(null);
                }
                repository.save(cmd.getEntity());
        }

}
