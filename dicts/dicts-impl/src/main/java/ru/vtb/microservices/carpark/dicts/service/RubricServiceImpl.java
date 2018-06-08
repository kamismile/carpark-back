package ru.vtb.microservices.carpark.dicts.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.repository.RubricRepository;
import ru.vtb.microservices.carpark.dicts.model.Rubric;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirzoevnik
 */
@Service
@Data
@Transactional
public class RubricServiceImpl implements RubricService {

    private final RubricRepository rubricRepository;

    @Autowired
    public RubricServiceImpl(RubricRepository rubricRepository) {
        this.rubricRepository = rubricRepository;
    }

    @Override
    @HystrixCommand(threadPoolKey = "findAllRubrics", commandKey = "DictExpressionCommand")
    public List<Rubric> findAll() {
        return new ArrayList<>(rubricRepository.findAll());
    }

    @Override
    public Rubric findByCode(String code) {
        return rubricRepository.findByCode(code);
    }

    @Override
    public void createRubric(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    @Override
    public void updateRubric(Rubric rubric) {
        rubricRepository.save(rubric);
    }
}
