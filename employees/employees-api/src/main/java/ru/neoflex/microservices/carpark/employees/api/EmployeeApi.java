package ru.neoflex.microservices.carpark.employees.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.microservices.carpark.commons.dto.PageResponse;
import ru.neoflex.microservices.carpark.employees.model.Employee;
import ru.neoflex.microservices.carpark.employees.model.EmployeeFilter;

import java.util.List;

/**
 * @author mirzoevnik
 */

public interface EmployeeApi {

    @GetMapping(value = "/employee/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Employee getByUserId(@PathVariable("userId") String userId);

    @DeleteMapping(value = "/employee/deactivate/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deactivate(@PathVariable("userId") String userId);

    @PutMapping(value = "/employee/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void add(Employee employee);

    @PatchMapping(value = "/employee/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void update(Employee employee);

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Employee> getAll(EmployeeFilter filter);

    @GetMapping(value = "/employees/page", produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest);
}