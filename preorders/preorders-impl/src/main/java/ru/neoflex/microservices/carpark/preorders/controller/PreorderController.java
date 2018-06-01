package ru.neoflex.microservices.carpark.preorders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.preorders.api.PreorderApi;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.service.PreorderService;

import java.util.List;

/**
 * @author mirzoevnik
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreorderController implements PreorderApi {

    private final PreorderService preorderService;

    @Override
    public List<Preorder> getAllPreorders(UserInfo userInfo) {
        return preorderService.findAll();
    }

    @Override
    public Preorder addPreorder(UserInfo userInfo, Preorder preorder) {
        preorder.setCreatedByUser(userInfo.getName());
        return preorderService.addPreorder(preorder);
    }

    @Override
    public void deletePreoder(UserInfo userInfo, Long id) {
        preorderService.deletePreoder(id);

    }

    @Override
    public Preorder updatePreorder(UserInfo userInfo, Preorder preorder) {
        return preorderService.updatePreorder(preorder);
    }
}
