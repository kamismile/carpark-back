package ru.neoflex.microservices.carpark.dicts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;

/**
 * @author mirzoevnik
 */
@Repository
public interface RubricRepository extends JpaRepository<Rubric, String> {

    Rubric findByCode(String code);
}
