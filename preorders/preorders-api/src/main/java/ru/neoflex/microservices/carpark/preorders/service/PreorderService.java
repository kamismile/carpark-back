package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.cars.model.PreorderType;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.cars.model.NextStatus;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * Сервис для работы с предзаказами.
 * @author Denis_Begun
 */
public interface PreorderService {

    List<Preorder> findAll();

    Preorder getPreorder(Long id);

    Preorder addPreorder(Preorder preorder, UserInfo userInfo);

    NextStatus getNextStatusForCar(Long carId);

    Preorder getEarliestPreorder(Long carId);

    Preorder getEarliestPreorderByType(Long carId, PreorderType type);
}
