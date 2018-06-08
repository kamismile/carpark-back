package ru.vtb.microservices.carpark.employees.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vtb.microservices.carpark.commons.dto.PageResponse;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.employees.model.EmployeeFilter;

import java.util.List;

/** Api for employee service.
 * @author mirzoevnik
 */
public interface EmployeeApi {

    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Employee getById(@PathVariable("id") Long Id);

    @DeleteMapping(value = "/employee/deactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deactivate(@PathVariable("id") Long userId);

    @PostMapping(value = "/employee/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Employee add(@RequestBody Employee employee);

    @PutMapping(value = "/employee/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void update(@RequestBody Employee employee);

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Employee> getAll(EmployeeFilter filter);

    @GetMapping(value = "/employeespage", produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest);

    @GetMapping(value = "/employeespage/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    String[] getFieldsName(EmployeeFilter filter, PageRequest pageRequest);

    @GetMapping(value = "/employeespage/fieldsFilter", produces = MediaType.APPLICATION_JSON_VALUE)
    String[] getFieldsFilterName(EmployeeFilter filter, PageRequest pageRequest);
}