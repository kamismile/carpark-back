/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.api;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * API для рест-сервиса предзаказов.
 *
 * @author Denis_Begun
 */
public interface PreorderApi {

    /**
     * Получение списка предзаказов.
     *
     * @param userInfo информаци о пользователе (из токена)
     * @return список предзаказов
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Preorder> getAllPreorders(UserInfo userInfo);

    /**
     * Полчение предзаказа по идентификатору.
     *
     * @param userInfo информаци о пользователе (из токена)
     * @param id       идентификатор предзаказа
     * @return предзаказ
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Preorder getPreorder(UserInfo userInfo, @PathVariable Long id);

    /**
     * Добавление предзаказа.
     *
     * @param userInfo информаци о пользователе (из токена)
     * @param preorder добавляемый предзаказ
     * @return предзаказ
     */
    //@PreAuthorize("hasPermission({{'preorder', #preorder}} , {'createPreorder'})")
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    Preorder addPreorder(UserInfo userInfo, @RequestBody Preorder preorder);

}
