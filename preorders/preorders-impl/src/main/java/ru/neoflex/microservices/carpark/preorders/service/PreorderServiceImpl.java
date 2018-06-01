package ru.neoflex.microservices.carpark.preorders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.repository.PreorderRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mirzoevnik
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
    public Preorder addPreorder(Preorder preorder) {
        return preorderRepository.save(preorder);
    }

    @Override
    public void deletePreoder(Long id) {
        preorderRepository.delete(id);
    }

    @Override
    public Preorder updatePreorder(Preorder preorder) {
        return preorderRepository.save(preorder);
    }

    /**
     * Вычисляем, нет ли конфликтов во времени по бронируемому автомобилю
     * @return
     */
    private void checkPreorder (Preorder preorder) {

        List<Preorder> existingList = preorderRepository.findByCarId(preorder.getCarId());
        List<Preorder> overlappingList = existingList.stream().filter(a -> preorder.overlaps(a)).collect(Collectors.toList());
        if (!overlappingList.isEmpty()) {
            throw new RuntimeException ("Required interval of dates is not available");
        }
    }
}
