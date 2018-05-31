package ru.neoflex.microservices.carpark.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.statemachine.data.RepositoryTransition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.cars.service.LifecycleService;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachineController {

    private LifecycleService lifecycleService;

    @GetMapping(value = "/transitions/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RepositoryTransition> getTransitions() {
        return null;
    }
}
