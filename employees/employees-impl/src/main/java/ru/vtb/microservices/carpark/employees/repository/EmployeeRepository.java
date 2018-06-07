package ru.vtb.microservices.carpark.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.employees.model.Employee;

/**
 * Employee repository.
 *
 * @author mirzoevnik
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        JpaSpecificationExecutor {

    Employee getByUserId(Long userId);
}
