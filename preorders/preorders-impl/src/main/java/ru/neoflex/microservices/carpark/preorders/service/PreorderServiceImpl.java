package ru.neoflex.microservices.carpark.preorders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.neoflex.microservices.carpark.cars.model.NextStatus;
import ru.neoflex.microservices.carpark.cars.model.NextStatusEvent;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.preorders.exception.CarNotAvailableException;
import ru.neoflex.microservices.carpark.preorders.exception.PreorderException;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.repository.PreorderRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса предварительных заказов автомобилей.
 *
 * @author mirzoevnik, Denis_Begun
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
        List<Preorder> existingList = preorderRepository.findByCarId(carId);

        Preorder earliestPreorder = existingList.stream()
                .min(Comparator.comparingLong(o -> o.getLeaseStartDate().getTime())).orElse(null);
        if (earliestPreorder != null) {
            Date nextStatusDate = earliestPreorder.getLeaseStartDate();
            String nextStatus = earliestPreorder.getType().getNextStatus();
            return new NextStatus(carId, nextStatus, nextStatusDate, earliestPreorder.getType());
        }
        return null;
    }

    /**
     * Проверяет, нет ли проблем с заказом.
     *
     * @param preorder проверяемый заказ
     */
    private void checkPreorder(Preorder preorder) {
        if (!preorder.isInFuture()) {
            throw new PreorderException("New preorder should start in future");
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
