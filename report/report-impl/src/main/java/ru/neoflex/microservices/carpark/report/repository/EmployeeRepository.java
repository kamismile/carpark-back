package ru.neoflex.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.microservices.carpark.employees.model.Employee;


/**
 * @author morenko
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUserId(String userId);
}
