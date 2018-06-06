package ru.neoflex.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.microservices.carpark.dicts.controller.model.Reference;

/**
 * @author rmorenko
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
