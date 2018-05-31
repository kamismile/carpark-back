package ru.neoflex.microservices.carpark.preorders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.repository.PreorderRepository;

import java.util.List;

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
}
