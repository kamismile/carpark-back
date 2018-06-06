package ru.neoflex.microservices.carpark.preorders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.preorders.exception.CarNotAvailableException;
import ru.neoflex.microservices.carpark.preorders.exception.PreorderException;
import ru.neoflex.microservices.carpark.preorders.model.NextStatus;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.repository.PreorderRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация сервиса предварительных заказов автомобилей.
 *
 * @author mirzoevnik, Denis_Begun
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreorderServiceImpl implements PreorderService {

    private final PreorderRepository preorderRepository;

    @Override
    public List<Preorder> findAll() {
        return preorderRepository.findAll();
    }

    @Override
    public Preorder getPreorder(Long id) {
        return preorderRepository.getOne(id);
    }

    @Override
    public Preorder addPreorder(Preorder preorder) {
        checkPreorder(preorder);
        preorder = preorderRepository.save(preorder);
        return preorder;
    }

    @Override
    public void deletePreoder(Long id) {
        Preorder preorder = preorderRepository.findOne(id);
        preorderRepository.delete(id);
    }

    @Override
    public Preorder updatePreorder(Preorder preorder) {
        checkPreorder(preorder);
        preorder = preorderRepository.save(preorder);
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
                .filter(a -> a.getId().equals(preorder.getId()))
                .filter(a -> preorder.overlaps(a)).collect(Collectors.toList());
        if (!overlappingList.isEmpty()) {
            throw new CarNotAvailableException(overlappingList.get(0).getLeaseStartDate(),
                    overlappingList.get(0).getLeaseEndDate());
        }
    }
}
