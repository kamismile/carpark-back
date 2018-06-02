package ru.neoflex.microservices.carpark.cars.config;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.data.*;
import org.springframework.statemachine.data.jpa.JpaRepositoryState;
import org.springframework.statemachine.data.jpa.JpaRepositoryTransition;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import ru.neoflex.microservices.carpark.cars.model.Events;
import ru.neoflex.microservices.carpark.cars.model.States;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {


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


    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
                .withStates()
                .initial(States.READY.name())
                .states(new HashSet<String>(Arrays.asList(States.READY.name(), States.IN_USE.name(), States.IN_SERVICE.name(), States.DECOMMISSIONED.name())));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.READY.name()).target(States.IN_USE.name())
                .event(Events.RENT.name())
                .and()
                .withExternal()
                .source(States.IN_USE.name()).target(States.READY.name())
                .event(Events.RETURN.name())
                .and()
                .withExternal()
                .source(States.READY.name()).target(States.IN_SERVICE.name())
                .event(Events.SERVICE.name())
                .and()
                .withExternal()
                .source(States.IN_SERVICE.name()).target(States.READY.name())
                .event(Events.RETURN.name())
                .and()
                .withExternal()
                .source(States.IN_SERVICE.name()).target(States.DECOMMISSIONED.name())
                .event(Events.RETIRE.name());
    }

}