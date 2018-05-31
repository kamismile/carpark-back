package ru.neoflex.microservices.carpark.access.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.neoflex.microservices.carpark.access.model.AccessExpresion;

import java.util.List;

/**
 * @author rmorenko.
 */
public interface AccessExpressionRepository extends JpaRepository<AccessExpresion, Long>, CrudRepository<AccessExpresion, Long> {

        List<AccessExpresion> findByOperation(String operation);
}
