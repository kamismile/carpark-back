package ru.neoflex.microservices.carpark.dicts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.dicts.controller.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.controller.repository.RubricRepository;
import ru.neoflex.microservices.carpark.dicts.controller.service.RubricService;
import ru.neoflex.microservices.carpark.dicts.controller.service.RubricServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class RubricServiceImplTest {

    private static final String CODE = "1";
    private static final String TITLE = "Title";
    private static final boolean SYSTEM = true;

    @Autowired
    RubricRepository rubricRepository = mock(RubricRepository.class);
    @Autowired
    RubricService rubricService;

    private Rubric rubric;
    private List<Rubric> rubricList;

    @BeforeMethod
    public void setUp() {
        reset(rubricRepository);
        rubricService = new RubricServiceImpl(rubricRepository);
        rubric = new Rubric();
        rubric.setCode(CODE);
        rubric.setTitle(TITLE);
        rubric.setSystem(SYSTEM);
        rubricList = new ArrayList<>();
        rubricList.add(rubric);
    }

    @Test
    public void testFindAll() {
        when(rubricRepository.findAll()).thenReturn(Arrays.asList(rubric));
        List<Rubric> arrayList = rubricService.findAll();
        Assert.assertNotNull(arrayList);
        verify(rubricRepository, atLeastOnce()).findAll();
    }

    @Test
    public void testFindByCode() {
        when(rubricRepository.findByCode(anyString())).thenReturn(rubric);
        when(rubricRepository.findByCode(null)).thenReturn(null);

        Rubric rubricAny = rubricService.findByCode(CODE);
        Rubric rubricNull = rubricService.findByCode(null);

        Assert.assertNotNull(rubricAny);
        Assert.assertNull(rubricNull);
        Assert.assertEquals(rubricAny.getCode(), CODE);

        verify(rubricRepository, times(1)).findByCode(eq(CODE));
        verify(rubricRepository, times(1)).findByCode(eq(null));
    }

    @Test
    public void testCreateRubric() {
        when(rubricRepository.save(rubric)).thenReturn(rubric);
        rubricService.createRubric(rubric);
        verify(rubricRepository, atLeastOnce()).save(eq(rubric));
    }

    @Test
    public void testUpdateRubric() {
        when(rubricRepository.save(rubric)).thenReturn(rubric);
        rubricService.updateRubric(rubric);
        verify(rubricRepository, atLeastOnce()).save(eq(rubric));
    }
}