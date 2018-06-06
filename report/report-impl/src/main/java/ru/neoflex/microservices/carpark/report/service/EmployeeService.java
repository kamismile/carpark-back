package ru.neoflex.microservices.carpark.report.service;

import groovy.util.logging.Slf4j;
import org.hibernate.HibernateException;
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
                String login = cmd.getEntity().getUser().getLogin();
                UserInfo newUser = cmd.getEntity().getUser();
                Employee oldEmployee = null;
                try {
                      oldEmployee = repository.findByUserLogin(login);
                } catch (HibernateException ex){
                        oldEmployee = null;
                }
                if (oldEmployee == null) {
                     newUser.setId(null);
                } else {
                    Long oldUserId = oldEmployee.getUser().getId();
                    newUser.setId(oldUserId);
                }
                newUser = userInfoRepository.save(newUser);
                cmd.getEntity().setUser(newUser);
                repository.save(oldEmployee);
        }

}
