package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.preorders.model.NextStatus;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface PreorderService {

    List<Preorder> findAll();

    Preorder getPreorder(Long id);

    Preorder addPreorder(Preorder preorder, UserInfo userInfo);

    public NextStatus getNextStatusForCar(Long carId);
}
