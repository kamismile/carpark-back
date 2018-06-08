package ru.vtb.microservices.carpark.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.vtb.microservices.carpark.cars.StateModelController;
import ru.vtb.microservices.carpark.cars.api.StateModelApi;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.cars.service.StateMachineService;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class StateModelControllerTest {

    private static final Long ID = 1L;
    private static final Long UPDATE_ID = 2L;
    private static final int TRANSITION_LIST_SIZE = 1;
    private static final int STATES_LIST_SIZE = 4;
    private static final int EVENTS_LIST_SIZE = 4;
    private static final String STATUS_CODE = "ready";
    private static final String STATE_DESCRIPTION = "В наличии";
    private static final String EVENT_DESCRIPTION = "В наличии";
    private Transition transition;
    private States stateFrom;
    private States stateTo;
    private Events event;
    private UserInfo userInfo;
    private List<Transition> transitionList;
    private List<States> statesList;

    @Autowired
    StateMachineService stateMachineService = mock(StateMachineService.class);
    @Autowired
    StateModelApi stateModelApi;

    @BeforeClass
    public void setUp() {
        reset(stateMachineService);
        stateModelApi = new StateModelController(stateMachineService);
        transition = new Transition();
        userInfo = new UserInfo();
        transition.setId(ID);
        transition.setFrom(States.DECOMMISSIONED);
        transition.setTo(States.READY);
        transition.setEvent(Events.RETIRE);
        transitionList = new ArrayList<>();
        transitionList.add(transition);
        userInfo.setLocationId(ID);
        userInfo.setName("Igor");
        userInfo.setRole("Administrator");
    }

    @Test
    public void testGetTransitions() {
        when(stateMachineService.getTransitions()).thenReturn(Arrays.asList(transition));
        List<Transition> transitionList = stateModelApi.getTransitions();

        Assert.assertNotNull(transitionList.size());
        Assert.assertEquals(transitionList.size(), TRANSITION_LIST_SIZE);

        verify(stateMachineService, atLeastOnce()).getTransitions();
    }

    @Test
    public void testGetPossibleStates() {
        List<States> statesList = stateModelApi.getPossibleStates();

        Assert.assertNotNull(statesList.size());
        Assert.assertEquals(statesList.size(), STATES_LIST_SIZE);

        Assert.assertEquals(statesList.get(0).getStatusCode(), STATUS_CODE);
        Assert.assertEquals(statesList.get(0).getDescription(), STATE_DESCRIPTION);
    }

    @Test
    public void testGetPossibleEvents() {
        List<Events> eventsList = stateModelApi.getPossibleEvents();

        Assert.assertNotNull(eventsList.size());
        Assert.assertEquals(eventsList.size(), EVENTS_LIST_SIZE);
        Assert.assertEquals(eventsList.get(0), Events.RENT);
        Assert.assertEquals(eventsList.get(1), Events.SERVICE);
        Assert.assertEquals(eventsList.get(2), Events.RETURN);
        Assert.assertEquals(eventsList.get(3), Events.RETIRE);
    }

    @Test
    public void testAddTransition() {
        when(stateMachineService.addTransition(isA(Transition.class))).thenReturn(transition);
        stateModelApi.addTransition(userInfo, transition);
        verify(stateMachineService, atLeastOnce()).addTransition(eq(transition));
    }

    @Test
    public void testUpdateTransition() {
        when(stateMachineService.updateTransition(isA(Transition.class))).thenReturn(transition);
        stateModelApi.updateTransition(userInfo, UPDATE_ID, transition);
        Assert.assertEquals(transition.getId(), UPDATE_ID);
        verify(stateMachineService, atLeastOnce()).updateTransition(eq(transition));
    }

    @Test
    public void testDeleteTransition() {
        doNothing().when(stateMachineService).deleteTransition(isA(Long.class));
        stateModelApi.deleteTransition(userInfo, ID);
        verify(stateMachineService, atLeastOnce()).deleteTransition(eq(ID));
    }
}
