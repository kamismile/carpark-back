package ru.vtb.microservices.carpark.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.cars.repository.StateMachineTransitionRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class StateMachineServiceImplTest {

    private static final Long ID = 1L;

    @Autowired
    private StateMachineTransitionRepository stateMachineTransitionRepository
            = mock(StateMachineTransitionRepository.class);
    @Autowired
    private StateMachineService stateMachineService;

    private Transition transition;

    @BeforeMethod
    public void setUp() throws Exception {
        reset(stateMachineTransitionRepository);
        stateMachineService = new StateMachineServiceImpl(stateMachineTransitionRepository);
        transition = new Transition();
        transition.setId(ID);
        transition.setFrom(States.READY);
        transition.setTo(States.DECOMMISSIONED);
        transition.setEvent(Events.RETIRE);
    }

    @Test
    public void testGetTransitions() throws Exception {
        when(stateMachineTransitionRepository.findAll()).thenReturn(Arrays.asList(transition));
        when(stateMachineService.getTransitions()).thenReturn(Arrays.asList(transition));

        List<Transition> transitionList = stateMachineService.getTransitions();

        Assert.assertNotNull(transitionList.size());
        Assert.assertEquals(transitionList.get(0).getId(), ID);

        verify(stateMachineTransitionRepository, atLeastOnce()).findAll();
    }

    @Test
    public void testAddTransition() throws Exception {
        when(stateMachineTransitionRepository.save(isA(Transition.class))).thenReturn(transition);
        stateMachineService.addTransition(transition);
        verify(stateMachineTransitionRepository, atLeastOnce()).save(eq(transition));
    }

    @Test
    public void testUpdateTransition() throws Exception {
        when(stateMachineTransitionRepository.save(isA(Transition.class))).thenReturn(transition);
        stateMachineService.updateTransition(transition);
        verify(stateMachineTransitionRepository, atLeastOnce()).save(eq(transition));
    }

    @Test
    public void testDeleteTransition() throws Exception {
        doNothing().when(stateMachineTransitionRepository).delete(isA(Long.class));
        stateMachineService.deleteTransition(ID);
        verify(stateMachineTransitionRepository, atLeastOnce()).delete(eq(ID));
    }
}