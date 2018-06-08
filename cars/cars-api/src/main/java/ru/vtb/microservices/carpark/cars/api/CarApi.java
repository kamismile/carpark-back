/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;

import java.util.List;

/**
 * Интерфейс контроллера автомашин.
 *
 * @author Denis_Begun
 */
public interface CarApi {

    /**
     * Получение списка автомобилей.
     *
     * @param userInfo  Информация о пользователе (из токена)
     * @param carFilter Фильтр по автомобилям (из параметров запроса)
     * @return Список автомобилей.
     */
    @PostFilter("hasPermission(filterObject, {'getCars_filter', {}})")
    List<Car> getCars(UserInfo userInfo, CarFilter carFilter);

    /**
     * Получение списка автомобилей (с пагинатором).
     *
     * @param userInfo  Информация о пользователе (из токена).
     * @param carFilter Фильтр по автомобилям (из параметров запроса).
     * @return Список автомобилей.
     */
    PageResponse<Car> getCars(UserInfo userInfo, CarFilter carFilter, PageRequest pageRequest);

    /**
     * Получене автомобиля по id.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Индентификатор автомобиля.
     * @return Сущность автомобиля.
     */
    Car getCar(UserInfo userInfo, @PathVariable(name = "id") Long id);

    /**
     * Обновление автомобиля.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Индентификатор автомобиля.
     * @param car      Сущность автомобиля.
     * @return Сущность автомобиля.
     */
    @PreAuthorize("hasPermission({{'id', #id}, {'car', #car}} , {'updateCar'})")
    Car updateCar(UserInfo userInfo, @PathVariable Long id, @RequestBody Car car);

    /**
     * Создание автомобиля
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param car      Сущность автомобиля.
     * @return Сущность автомобиля.
     */
    @PreAuthorize("hasPermission({{'car', #car}} , {'createCar'})")
    Car createCar(UserInfo userInfo, @RequestBody Car car);

    /**
     * Удаление автомобиля.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Индентификатор автомобиля.
     */
    @PreAuthorize("hasPermission({{'id', #id}} , {'deleteCar'})")
    void deleteCar(UserInfo userInfo, @PathVariable Long id);

    /**
     * Перевод автомобиля по статусам.
     *
     * @param userInfo    Информация о пользователе (из токена).
     * @param id          Индентификатор автомобиля.
     * @param stringEvent Имя отсылаемого события
     * @return Сущность автомобиля.
     */
    @PreAuthorize("hasPermission({{'id', #id}, {'stringEvent', #stringEvent}}, {'changeCarState'})")
    Car changeCarState(UserInfo userInfo, @PathVariable("id") Long id, @PathVariable("stringEvent") String stringEvent);

    /**
     * Синхонизация текущего состояния автопарка для потребителей в других сервисах.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @return Список автомобилей.
     */
    @PreAuthorize("hasAuthority('administrator')")
    List<Car> uploadAllCars(UserInfo userInfo);

}
