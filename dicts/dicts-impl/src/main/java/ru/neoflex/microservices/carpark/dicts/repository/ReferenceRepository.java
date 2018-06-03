package ru.neoflex.microservices.carpark.dicts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, String> {

    List<Reference> findByRubric(Rubric rubric);
    Reference findByCode(String code);
}
