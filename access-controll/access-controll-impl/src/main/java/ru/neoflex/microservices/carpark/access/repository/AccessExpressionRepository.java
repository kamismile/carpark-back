package ru.neoflex.microservices.carpark.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.neoflex.microservices.carpark.access.model.AccessExpression;

import java.util.List;

/**
 * @author rmorenko.
 */
public interface AccessExpressionRepository extends JpaRepository<AccessExpression, Long>, CrudRepository<AccessExpression, Long> {

        List<AccessExpression> findByOperation(String operation);
}
