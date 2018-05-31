package ru.neoflex.microservices.carpark.dicts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.repository.ReferenceRepository;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceService;

import java.util.List;

/**
 * @author mirzoevnik
 */
@Service
public class ReferenceServiceImpl implements ReferenceService {

    private final ReferenceRepository referenceRepository;

    @Autowired
    public ReferenceServiceImpl(ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    public List<Reference> findByRubric(Rubric rubric) {
        return referenceRepository.findByRubric(rubric);
    }
}
