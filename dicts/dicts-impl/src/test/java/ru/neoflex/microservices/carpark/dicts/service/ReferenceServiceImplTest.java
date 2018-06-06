package ru.neoflex.microservices.carpark.dicts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.ReferenceCommand;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.repository.ReferenceRepository;
import ru.neoflex.microservices.carpark.dicts.sender.DictSender;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceService;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceServiceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
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
    private List<Reference> referenceList;
    private ReferenceCommand referenceCommand;

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
        reference.setRubric(rubric);
        referenceList = new ArrayList<>();
        referenceList.add(reference);
        referenceCommand = new ReferenceCommand();
        referenceCommand.setCommand(Command.DELETE);
        referenceCommand.setEntity(reference);
    }

    @Test
    public void testFindByRubric() {
        when(referenceService.findByRubric(rubric)).thenReturn(Arrays.asList(reference));
        when(referenceService.findByRubric(null)).thenReturn(null);

        List<Reference> referenceAny = referenceService.findByRubric(rubric);
        List<Reference> referencesNull = referenceService.findByRubric(null);

        Assert.assertNotNull(referenceAny);
        Assert.assertNull(referencesNull);
        Assert.assertEquals(referenceAny.get(0).getCode(), CODE);

        verify(referenceRepository, atLeastOnce()).findByRubric(eq(rubric));
        verify(referenceRepository, atMost(1)).findByRubric(eq(null));
    }

    @Test
    public void testCreateReference() {
        when(referenceRepository.save(reference)).thenReturn(reference);
        doNothing().when(sender).send(isA(String.class), isA(ReferenceCommand.class));

        referenceService.createReference(reference);

        verify(referenceRepository, atLeastOnce()).save(eq(reference));
    }

    @Test
    public void testUpdateReference() {
        when(referenceRepository.findByCode(anyString())).thenReturn(reference);
        doNothing().when(sender).send(isA(String.class), isA(ReferenceCommand.class));

        referenceService.updateReference(reference);

        verify(referenceRepository, atLeastOnce()).save(eq(reference));
    }

    @Test
    public void testDisableReference() {
        when(referenceRepository.findByCode(anyString())).thenReturn(reference);
        when(referenceRepository.save(reference)).thenReturn(reference);

        referenceService.disableReference(CODE);

        verify(referenceRepository, atLeastOnce()).findByCode(eq(CODE));
        verify(referenceRepository, atLeastOnce()).save(eq(reference));
    }
}