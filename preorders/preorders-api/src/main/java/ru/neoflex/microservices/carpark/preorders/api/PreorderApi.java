package ru.neoflex.microservices.carpark.preorders.api;

import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface PreorderApi {

    List<Preorder> getAllPreorders(UserInfo userInfo);

    Preorder addPreorder(UserInfo userInfo, Preorder preorder);

    void deletePreoder(UserInfo userInfo, Long id);

    Preorder updatePreorder (UserInfo userInfo, Preorder preorder);
}
