package ru.vtb.microservices.carpark.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.microservices.carpark.employees.model.Location;

/**
 * @author morenko
 */
public interface LocationRepository extends JpaRepository<Location,Long> {

        Location findByAddress(String address);
}
