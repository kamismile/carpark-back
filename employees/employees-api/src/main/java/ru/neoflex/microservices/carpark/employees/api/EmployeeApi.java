package ru.neoflex.microservices.carpark.employees.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.employees.model.Employee;

/**
 * @author mirzoevnik
 */

public interface EmployeeApi {

    @GetMapping(value = "/employee/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Employee getByUserId(@PathVariable("userId") String userId);

    @DeleteMapping(value = "/employee/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete (@PathVariable("userId") String userId);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void add(Employee employee);

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void update(Employee employee);



}
