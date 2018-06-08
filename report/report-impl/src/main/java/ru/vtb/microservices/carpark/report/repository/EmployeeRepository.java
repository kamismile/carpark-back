package ru.vtb.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.microservices.carpark.employees.model.Employee;


/**
 * @author morenko
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        Employee findByUserId(Long userId);

        Employee findByUserLogin(String login);

}
