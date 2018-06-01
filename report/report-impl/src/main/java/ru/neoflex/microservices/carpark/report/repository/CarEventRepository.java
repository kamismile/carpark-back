package ru.neoflex.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.microservices.carpark.report.model.CarEvent;

/**
 * @author rmorenko
 */
public interface CarEventRepository extends JpaRepository<CarEvent, Long> {
}
