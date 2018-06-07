package ru.neoflex.microservices.carpark.preorders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.preorders.api.PreorderApi;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.service.PreorderService;


import java.util.Date;
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
    public Preorder getPreorder(UserInfo userInfo, @PathVariable(name = "id") Long id) {
        return preorderService.getPreorder(id);
    }

    @Override
    public Preorder addPreorder(UserInfo userInfo, @RequestBody Preorder preorder) {
        preorder.setCreatedByUser(userInfo.getName());
        return preorderService.addPreorder(preorder, userInfo);
    }
}
