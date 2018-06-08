/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostFilter("hasPermission(filterObject, {'getCars_filter', {}})")
    List<Car> getCars(UserInfo userInfo, CarFilter carFilter);

    /**
     * Получение списка автомобилей (с пагинатором).
     *
     * @param userInfo  Информация о пользователе (из токена).
     * @param carFilter Фильтр по автомобилям (из параметров запроса).
     * @return Список автомобилей.
     */
    @GetMapping(value = "/carspage/", produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Car> getCars(UserInfo userInfo, CarFilter carFilter, PageRequest pageRequest);

    /**
     * Получене автомобиля по id.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Индентификатор автомобиля.
     * @return Сущность автомобиля.
     */
    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Car getCar(UserInfo userInfo, @PathVariable(name = "id") Long id);

    /**
     * Обновление автомобиля.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Индентификатор автомобиля.
     * @param car      Сущность автомобиля.
     * @return Сущность автомобиля.
     */
    @PutMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'car', #car}} , {'updateCar'})")
    Car updateCar(UserInfo userInfo, @PathVariable Long id, @RequestBody Car car);

    /**
     * Создание автомобиля
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param car      Сущность автомобиля.
     * @return Сущность автомобиля.
     */
    @PostMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'car', #car}} , {'createCar'})")
    Car createCar(UserInfo userInfo, @RequestBody Car car);

    /**
     * Удаление автомобиля.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @param id       Индентификатор автомобиля.
     */
    @DeleteMapping(value = "/cars/{id}")
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
    @PatchMapping(value = "/cars/{id}/{stringEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'stringEvent', #stringEvent}}, {'changeCarState'})")
    Car changeCarState(UserInfo userInfo, @PathVariable("id") Long id, @PathVariable("stringEvent") String stringEvent);

    /**
     * Синхонизация текущего состояния автопарка для потребителей в других сервисах.
     *
     * @param userInfo Информация о пользователе (из токена).
     * @return Список автомобилей.
     */
    @GetMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('administrator')")
    List<Car> uploadAllCars(UserInfo userInfo);

}
