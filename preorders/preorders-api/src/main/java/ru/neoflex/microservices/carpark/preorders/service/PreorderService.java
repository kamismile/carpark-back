package ru.neoflex.microservices.carpark.preorders.service;

import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface PreorderService {

    List<Preorder> findAll();

    Preorder addPreorder(Preorder preorder);

    void deletePreoder(Long id);

    Preorder updatePreorder(Preorder preorder);
}
