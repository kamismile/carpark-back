package ru.neoflex.microservices.carpark.preorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * Объект доступа к данным о презаказх.
 * @author Denis_Begun
 */
@Repository
public interface PreorderRepository extends JpaRepository<Preorder, Long> {

    List<Preorder> findByCarId(Long carId);
}
