package ru.vtb.microservices.carpark.cars.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import ru.vtb.microservices.carpark.cars.model.Events;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;
import ru.vtb.microservices.carpark.cars.service.StateMachineService;
import ru.vtb.microservices.carpark.cars.model.States;
import ru.vtb.microservices.carpark.cars.model.Transition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    private final StateMachineService stateMachineService;


    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
                .withStates()
                .initial(States.READY.name())
                .states(Arrays.stream(States.values())
                        .map(a -> a.name())
                        .collect(Collectors.toSet()));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        List<Transition> transitionFromRepository = stateMachineService.getTransitions();
        for (Transition tr : transitionFromRepository) {
            transitions
                    .withExternal()
                    .source(tr.getFrom().name()).target(tr.getTo().name())
                    .event(tr.getEvent().name())
                    .and();
        }
    }
}

//transitions
//        .withExternal()
//        .source(States.READY.name()).target(States.IN_USE.name())
//        .event(Events.RENT.name())
//        .and()
//        .withExternal()
//        .source(States.IN_USE.name()).target(States.READY.name())
//        .event(Events.RETURN.name())
//        .and()
//        .withExternal()
//        .source(States.READY.name()).target(States.IN_SERVICE.name())
//        .event(Events.SERVICE.name())
//        .and()
//        .withExternal()
//        .source(States.IN_SERVICE.name()).target(States.READY.name())
//        .event(Events.RETURN.name())
//        .and()
//        .withExternal()
//        .source(States.IN_SERVICE.name()).target(States.DECOMMISSIONED.name())
//        .event(Events.RETIRE.name());

//    @Autowired
//    private StateRepository<JpaRepositoryState> stateRepository;
//
//    @Autowired
//    private TransitionRepository<JpaRepositoryTransition> transitionRepository;
//
//    @Override
//    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
//        model
//                .withModel()
//                .factory(modelFactory());
//        addInitialConfig();
//    }
//
//    @Bean
//    public StateMachineModelFactory<String, String> modelFactory() {
//        return new RepositoryStateMachineModelFactory(stateRepository, transitionRepository);
//    }
//
//    private void addInitialConfig() {
//        JpaRepositoryState ready = new JpaRepositoryState(States.READY.name(), true);
//        JpaRepositoryState inUse = new JpaRepositoryState(States.IN_USE.name());
//        JpaRepositoryState inService = new JpaRepositoryState(States.IN_SERVICE.name());
//        JpaRepositoryState decommissioned = new JpaRepositoryState(States.DECOMMISSIONED.name());
//
//        stateRepository.save(ready);
//        stateRepository.save(inUse);
//        stateRepository.save(inService);
//        stateRepository.save(decommissioned);
//
//        JpaRepositoryTransition t1 = new JpaRepositoryTransition(ready, inUse, Events.RENT.name());
//        JpaRepositoryTransition t2 = new JpaRepositoryTransition(ready, inService, Events.SERVICE.name());
//        JpaRepositoryTransition t3 = new JpaRepositoryTransition(inUse, ready, Events.RETURN.name());
//        JpaRepositoryTransition t4 = new JpaRepositoryTransition(inService, ready, Events.RETURN.name());
//        JpaRepositoryTransition t5 = new JpaRepositoryTransition(inService, decommissioned, Events.RETIRE.name());
//
//        transitionRepository.save(t1);
//        transitionRepository.save(t2);
//        transitionRepository.save(t3);
//        transitionRepository.save(t4);
//        transitionRepository.save(t5);
//
//    }
//
//    @Bean
//    public StateMachineListener<String, String> listener() {
//        return new StateMachineListenerAdapter<String, String>() {
//            @Override
//            public void stateChanged(State<String, String> from, State<String, String> to) {
//                System.out.println("State changed to " + to.getId());
//            }
//        };
//    }