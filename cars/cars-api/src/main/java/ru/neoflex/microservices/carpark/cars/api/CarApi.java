/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.cars.api;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

import java.util.List;

/**
 * Интерфейс контроллера автомашин.
 *
 * @author Denis_Begun
 */

public interface CarApi {

    @GetMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostFilter("hasPermission(filterObject, {'getCars_filter', {}})")
    List<Car> getCars(UserInfo userInfo);

    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Car getCar(UserInfo userInfo, @PathVariable Long id);

    @PutMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'car', #car}} , {'updateCar'})")
    Car updateCar(UserInfo userInfo, @PathVariable Long id, @RequestBody Car car);

    @PostMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'car', #car}} , {'createCar'})")
    Car createCar(UserInfo userInfo, @RequestBody Car car);

    @DeleteMapping(value = "/cars/{id}")
    @PreAuthorize("hasPermission({{'id', #id}} , {'deleteCar'})")
    void deleteCar(UserInfo userInfo, @PathVariable Long id);

    @PatchMapping(value = "/cars/{id}/{event}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'stringEvent', #stringEvent}}, {'changeCarState'})")
    Car changeCarState(UserInfo userInfo, @PathVariable Long id, @PathVariable String stringEvent);
}
