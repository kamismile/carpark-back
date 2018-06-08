/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.service;

import ru.vtb.microservices.carpark.cars.model.NextStatus;
import ru.vtb.microservices.carpark.cars.model.PreorderType;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * Сервис для работы с предзаказами.
 *
 * @author Denis_Begun
 */
public interface PreorderService {

    /**
     * Получение списка предзаказов.
     *
     * @return список предзаказов
     */
    List<Preorder> findAll();

    /**
     * Полчение предзаказов по id.
     *
     * @param id индентификатор предзаказа
     * @return предзаказ
     */
    Preorder getPreorder(Long id);

    /**
     * Добавление предзаказа.
     *
     * @param preorder предзаказ
     * @param userInfo информация о пользователе
     * @return добавленный презаказ
     */
    Preorder addPreorder(Preorder preorder, UserInfo userInfo);

    /**
     * Получение объекта следующего статуса по автомобилю.
     *
     * @param carId идентификатор автомобиля
     * @return объект следующего статуса
     */
    NextStatus getNextStatusForCar(Long carId);

    /**
     * Полчение самого раннего будущего предзаказа по автомобилю.
     *
     * @param carId идентификатор автомобиля
     * @return объект предзаказа
     */
    Preorder getEarliestPreorder(Long carId);

    /**
     * Полчение самого раннего будущего предзаказа по автомобилю с учетом типа предзаказа.
     *
     * @param carId идентификатор автомобиля
     * @param type  тип предзаказа
     */
    Preorder getEarliestPreorderByType(Long carId, PreorderType type);
}
