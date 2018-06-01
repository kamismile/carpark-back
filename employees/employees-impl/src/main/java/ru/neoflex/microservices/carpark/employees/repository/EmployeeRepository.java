package ru.neoflex.microservices.carpark.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.microservices.carpark.employees.model.Employee;

/**
 * @author mirzoevnik
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getByUserId(String userId);
}
