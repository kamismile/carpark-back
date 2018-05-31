package ru.neoflex.microservices.carpark.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.microservices.carpark.employees.model.Location;

/**
 * @author mirzoevnik
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
