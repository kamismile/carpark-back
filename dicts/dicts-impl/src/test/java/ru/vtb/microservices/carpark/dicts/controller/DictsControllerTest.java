package ru.vtb.microservices.carpark.dicts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vtb.microservices.carpark.dicts.api.DictsApi;
import ru.vtb.microservices.carpark.dicts.model.Reference;
import ru.vtb.microservices.carpark.dicts.model.Rubric;
import ru.vtb.microservices.carpark.dicts.service.ReferenceService;
import ru.vtb.microservices.carpark.dicts.service.RubricService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class DictsControllerTest {

    private static final String CODE = "1";
    private static final String UPDATE_CODE = "2";
    private static final String TITLE = "Title";
    private static final boolean SYSTEM = true;
    private static final boolean ACTIVE = true;
    private static final int RUBRICS_LIST_SIZE = 2;
    private static final int REFERENCES_LIST_SIZE = 1;
    private Reference reference;
    private Rubric rubric;
    private List<Rubric> rubricList;

    @Autowired
    DictsApi dictsApi;
    @Autowired
    RubricService rubricService = mock(RubricService.class);
    @Autowired
    ReferenceService referenceService = mock(ReferenceService.class);

    @BeforeMethod
    public void setUp() {
        reset(rubricService);
        reset(referenceService);
        dictsApi = new DictsController(rubricService, referenceService);
        rubric = new Rubric();
        reference = new Reference();
        rubric.setCode(CODE);
        rubric.setTitle(TITLE);
        rubric.setSystem(SYSTEM);
        rubricList = new ArrayList<>();
        rubricList.add(rubric);
        reference.setCode(CODE);
        reference.setTitle(TITLE);
        reference.setSystem(SYSTEM);
        reference.setActive(ACTIVE);
        reference.setRubric(rubric);
    }

    @Test
    public void testGetRubrics() {
        when(rubricService.findAll()).thenReturn(Arrays.asList(rubric, rubric));
        List<Rubric> rubricList = dictsApi.getRubrics();

        Assert.assertNotNull(rubricList.size());
        Assert.assertEquals(RUBRICS_LIST_SIZE, rubricList.size());

        Assert.assertEquals(CODE, rubricList.get(0).getCode());
        Assert.assertEquals(TITLE, rubricList.get(0).getTitle());
        Assert.assertEquals(SYSTEM, true);

        Assert.assertEquals(CODE, rubricList.get(1).getCode());
        Assert.assertEquals(TITLE, rubricList.get(1).getTitle());
        Assert.assertEquals(SYSTEM, true);

        verify(rubricService, atLeastOnce()).findAll();
    }

    @Test
    public void testGetReferencesByRubric() {
        when(rubricService.findByCode(CODE)).thenReturn(rubric);
        when(referenceService.findByRubric(rubric)).thenReturn(Arrays.asList(reference));

        List<Reference> referenceList = dictsApi.getReferencesByRubric(rubric.getCode());

        Assert.assertNotNull(referenceList.size());
        Assert.assertEquals(REFERENCES_LIST_SIZE, referenceList.size());

        Assert.assertEquals(CODE, referenceList.get(0).getCode());
        Assert.assertEquals(TITLE, referenceList.get(0).getTitle());
        Assert.assertEquals(CODE, referenceList.get(0).getRubric().getCode());
        Assert.assertEquals(TITLE, referenceList.get(0).getRubric().getTitle());
        Assert.assertEquals(SYSTEM, true);
        Assert.assertEquals(ACTIVE, true);

        verify(rubricService, atLeastOnce()).findByCode(eq(rubric.getCode()));
        verify(referenceService, atLeastOnce()).findByRubric(eq(rubric));
    }

    @Test
    public void testUpdateReference() {
        doNothing().when(referenceService).updateReference(isA(Reference.class));
        dictsApi.updateReference(UPDATE_CODE, reference);
        Assert.assertEquals(reference.getCode(), UPDATE_CODE);
        verify(referenceService, atLeastOnce()).updateReference(eq(reference));
    }

    @Test
    public void testUpdateRubric() {
        doNothing().when(rubricService).updateRubric(isA(Rubric.class));
        dictsApi.updateRubric(UPDATE_CODE, rubric);
        Assert.assertEquals(rubric.getCode(), UPDATE_CODE);
        verify(rubricService, atLeastOnce()).updateRubric(eq(rubric));
    }

    @Test
    public void testCreateRubric() {
        doNothing().when(rubricService).createRubric(isA(Rubric.class));
        dictsApi.createRubric(rubric);
        verify(rubricService, atLeastOnce()).createRubric(eq(rubric));
    }

    @Test
    public void testCreateReference() {
        doNothing().when(referenceService).createReference(isA(Reference.class));
        dictsApi.createReference(reference);
        verify(referenceService, atLeastOnce()).createReference(eq(reference));
    }

    @Test
    public void testDisableReference() {
        doNothing().when(referenceService).disableReference(isA(String.class));
        dictsApi.disableReference(CODE);
        verify(referenceService, atLeastOnce()).disableReference(eq(CODE));
    }
}