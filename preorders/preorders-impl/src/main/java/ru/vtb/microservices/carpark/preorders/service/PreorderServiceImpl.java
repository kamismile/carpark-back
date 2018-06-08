/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vtb.microservices.carpark.cars.model.NextStatus;
import ru.vtb.microservices.carpark.cars.model.NextStatusEvent;
import ru.vtb.microservices.carpark.cars.model.PreorderType;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.commons.model.Command;
import ru.vtb.microservices.carpark.preorders.exception.CarNotAvailableException;
import ru.vtb.microservices.carpark.preorders.exception.PreorderException;
import ru.vtb.microservices.carpark.preorders.model.Preorder;
import ru.vtb.microservices.carpark.preorders.repository.PreorderRepository;
import ru.vtb.microservices.carpark.preorders.repository.PreorderRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса предварительных заказов автомобилей.
 *
 * @author Denis_Begun
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreorderServiceImpl implements PreorderService {

    private final PreorderRepository preorderRepository;

    private final KafkaProducerService kafkaService;

    @Override
    public List<Preorder> findAll() {
        return preorderRepository.findAll();
    }

    @Override
    public Preorder getPreorder(Long id) {
        return preorderRepository.getOne(id);
    }

    @Override
    public Preorder addPreorder(Preorder preorder, UserInfo userInfo) {
        checkPreorder(preorder);
        preorder = preorderRepository.save(preorder);
        makeNotification(userInfo, preorder.getCarId());
        return preorder;
    }

    @Override
    public NextStatus getNextStatusForCar(Long carId) {
        Preorder earliestPreorder = getEarliestPreorder(carId);
        if (earliestPreorder != null) {
            Date nextStatusDate = earliestPreorder.getLeaseStartDate();
            String nextStatus = earliestPreorder.getType().getNextStatus();
            return new NextStatus(carId, nextStatus, nextStatusDate, earliestPreorder.getType());
        }
        return null;
    }

    @Override
    public Preorder getEarliestPreorder(Long carId) {
        List<Preorder> existingList = preorderRepository.findByCarId(carId);
        Date now = new Date();
        if (existingList.isEmpty()) {
            return null;
        } else {
            return existingList.stream()
                    .filter(p -> p.getLeaseStartDate().compareTo(now) > 0)
                    .min(Comparator.comparingLong(o -> o.getLeaseStartDate().getTime()))
                    .orElse(null);
        }
    }

    @Override
    public Preorder getEarliestPreorderByType(Long carId, PreorderType type) {
        List<Preorder> existingList = preorderRepository.findByCarId(carId);
        Date now = new Date();
        if (existingList.isEmpty()) {
            return null;
        } else {
            return existingList.stream().filter(preorder -> type == preorder.getType())
                    .filter(p -> p.getLeaseStartDate().compareTo(now) > 0)
                    .min(Comparator.comparingLong(o -> o.getLeaseStartDate().getTime()))
                    .orElse(null);
        }
    }

    /**
     * Проверяет, нет ли проблем с заказом.
     *
     * @param preorder проверяемый заказ
     */
    private void checkPreorder(Preorder preorder) {
        if (!preorder.isInFuture()) {
            throw new PreorderException("Дата начала бронирования не может быть меньше текущей");
        }

        if (!preorder.isDurationOk()) {
            throw new PreorderException("Дата окончания бронирования должна быть позже даты начала");
        }

        List<Preorder> existingList = preorderRepository.findByCarId(preorder.getCarId());
        List<Preorder> overlappingList = existingList.stream()
                .filter(a -> !a.getId().equals(preorder.getId()))
                .filter(a -> preorder.overlaps(a)).collect(Collectors.toList());
        if (!overlappingList.isEmpty()) {
            throw new CarNotAvailableException(overlappingList.get(0).getLeaseStartDate(),
                    overlappingList.get(0).getLeaseEndDate());
        }
    }

    /**
     * Отсылка оповещения об изменении следующего статутса.
     *
     * @param userInfo информация о пользователе
     * @param carId    идентификатор автомобиля
     */
    private void makeNotification(UserInfo userInfo, @PathVariable Long carId) {
        NextStatus ns = getNextStatusForCar(carId);
        if (ns != null) {
            NextStatusEvent nse = new NextStatusEvent();
            nse.setCommand(Command.UPDATE);
            nse.setEntity(ns);
            nse.setUserInfo(userInfo);
            nse.setMessageDate(new Date());
            kafkaService.sendMessage(nse);
        }
    }
}
