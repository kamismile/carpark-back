/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.api;


import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarFilter;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.cars.model.CarFilter;

import java.util.List;

/**
 * Интерфейс контроллера автомашин.
 *
 * @author Denis_Begun
 */

public interface CarApi {

    @GetMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostFilter("hasPermission(filterObject, {'getCars_filter', {}})")
    List<Car> getCars(UserInfo userInfo, CarFilter carFilter);

    @GetMapping(value = "/carspage/", produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Car> getCars(UserInfo userInfo, CarFilter carFilter, PageRequest pageRequest);

    @GetMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Car getCar(UserInfo userInfo, @PathVariable(name="id") Long id);

    @PutMapping(value = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'car', #car}} , {'updateCar'})")
    Car updateCar(UserInfo userInfo, @PathVariable Long id, @RequestBody Car car);

    @PostMapping(value = "/cars/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'car', #car}} , {'createCar'})")
    Car createCar(UserInfo userInfo, @RequestBody Car car);

    @DeleteMapping(value = "/cars/{id}")
    @PreAuthorize("hasPermission({{'id', #id}} , {'deleteCar'})")
    void deleteCar(UserInfo userInfo, @PathVariable Long id);

    @PatchMapping(value = "/cars/{id}/{stringEvent}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission({{'id', #id}, {'stringEvent', #stringEvent}}, {'changeCarState'})")
    Car changeCarState(UserInfo userInfo, @PathVariable("id") Long id, @PathVariable("stringEvent") String stringEvent);

    @GetMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('administrator')")
    List<Car> uploadAllCars(UserInfo userInfo);

}