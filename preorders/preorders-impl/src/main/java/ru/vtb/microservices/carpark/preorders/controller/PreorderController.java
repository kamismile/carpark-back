/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.preorders.api.PreorderApi;
import ru.vtb.microservices.carpark.preorders.model.Preorder;
import ru.vtb.microservices.carpark.preorders.service.PreorderService;


import java.util.List;

/**
 * Rest-контроллер для обработки предзаказов.
 *
 * @author Denis_Begun
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
