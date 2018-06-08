package ru.vtb.microservices.carpark.dicts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.model.Rubric;

import java.util.List;

/**
 * @author mirzoevnik
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, String> {

    List<Reference> findByRubric(Rubric rubric);
    Reference findByCode(String code);
}
