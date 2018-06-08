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

    Employee getById(@PathVariable("id") Long Id);

    void deactivate(@PathVariable("id") Long userId);

    Employee add(@RequestBody Employee employee);

    void update(@RequestBody Employee employee);

    List<Employee> getAll(EmployeeFilter filter);

    PageResponse<Employee> getAll(EmployeeFilter filter, PageRequest pageRequest);

    String[] getFieldsName(EmployeeFilter filter, PageRequest pageRequest);

    String[] getFieldsFilterName(EmployeeFilter filter, PageRequest pageRequest);
}