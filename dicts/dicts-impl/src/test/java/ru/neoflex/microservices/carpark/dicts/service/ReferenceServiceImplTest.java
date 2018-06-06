package ru.neoflex.microservices.carpark.dicts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.dicts.controller.model.Reference;
import ru.neoflex.microservices.carpark.dicts.controller.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.controller.repository.ReferenceRepository;
import ru.neoflex.microservices.carpark.dicts.controller.sender.DictSender;
import ru.neoflex.microservices.carpark.dicts.controller.service.ReferenceService;
import ru.neoflex.microservices.carpark.dicts.controller.service.ReferenceServiceImpl;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class ReferenceServiceImplTest {

    private static final String CODE = "1";
    private static final String TITLE = "Title";
    private static final boolean SYSTEM = true;
    private static final boolean ACTIVE = true;

    @Autowired
    ReferenceRepository referenceRepository = mock(ReferenceRepository.class);
    @Autowired
    private ReferenceService referenceService;
    @Autowired
    private DictSender sender = mock(DictSender.class);

    private Reference reference;
    private Rubric rubric;

    @BeforeMethod
    public void setUp() {
        reset(referenceRepository);
        reset(sender);
        referenceService = new ReferenceServiceImpl(referenceRepository, sender);
        rubric = new Rubric();
        reference = new Reference();
        rubric.setCode(CODE);
        rubric.setTitle(TITLE);
        rubric.setSystem(SYSTEM);
        reference.setCode(CODE);
        reference.setTitle(TITLE);
        reference.setActive(ACTIVE);
        reference.setSystem(SYSTEM);

    }

}