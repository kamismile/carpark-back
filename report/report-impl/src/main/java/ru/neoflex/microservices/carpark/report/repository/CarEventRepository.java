package ru.neoflex.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.microservices.carpark.report.model.CarEvent;

/**
 * @author rmorenko
 */
@Repository
public interface CarEventRepository extends JpaRepository<CarEvent, Long> {
}
