package ru.vtb.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.microservices.carpark.dicts.model.Reference;

/**
 * @author rmorenko
 */
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
