package ru.neoflex.microservices.carpark.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.statemachine.data.RepositoryTransition;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.cars.api.StateModelApi;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;
import ru.neoflex.microservices.carpark.cars.model.Transition;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;
import ru.neoflex.microservices.carpark.cars.service.StateMachineService;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateModelController implements StateModelApi {

    private final StateMachineService stateMachineService;

    @Override
    @GetMapping(value = "/sm/transitions/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transition> getTransitions() {
        return stateMachineService.getTransitions();
    }

    @Override
    @GetMapping(value = "/sm/states/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<States> getPossibleStates() {
        return Arrays.asList(States.values());
    }

    @Override
    @GetMapping(value = "/sm/events/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Events> getPossibleEvents() {
        return Arrays.asList(Events.values());
    }

    @Override
    @PostMapping(value = "/sm/transitions/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Transition addTransition(UserInfo userInfo, @RequestBody Transition transition) {
        return stateMachineService.addTransition(transition);
    }

    @Override
    @PutMapping(value = "/sm/transitions/{id}")
    public Transition updateTransition(UserInfo userInfo, @PathVariable Long id, @RequestBody Transition transition) {
        transition.setId(id);
        return stateMachineService.updateTransition(transition);
    }

    @Override
    @DeleteMapping(value = "/sm/transitions/{id}")
    public void deleteTransition(UserInfo userInfo, @PathVariable Long id) {
        stateMachineService.deleteTransition(id);
    }
}
