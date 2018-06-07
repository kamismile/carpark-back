package ru.vtb.microservices.carpark.report.service;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.microservices.carpark.cars.model.Car;
import ru.vtb.microservices.carpark.employees.model.Employee;
import ru.vtb.microservices.carpark.report.model.CarCommand;
import ru.vtb.microservices.carpark.report.model.CarEvent;
import ru.vtb.microservices.carpark.report.repository.CarEventRepository;
import ru.vtb.microservices.carpark.report.repository.EmployeeRepository;


/**
 * @author rmorenko
 */
@Service
@Data
public class CarEventResourceService {

   @Autowired
   private CarEventRepository carEventRepository;

   @Autowired
   private EmployeeRepository employeeRepository;

   public void save(CarCommand carCommand){
      Car car = carCommand.getEntity();
      CarEvent carEvent = new CarEvent();
      carEvent.setMessageDate(carCommand.getMessageDate());
      carEvent.setUserName(carCommand.getUserInfo().getName());
      try {
         Employee employee = employeeRepository.findByUserLogin(carCommand.getUserInfo().getName());
         carEvent.setEmployee(employee);
      } catch(Exception ex) {
         carEvent.setEmployee(null);
      }

      BeanUtils.copyProperties(car,carEvent);
      carEvent.setId(null);
      carEvent.setCarId(car.getId());
      carEvent.setMessageType(carCommand.getCommand().toString());
      carEventRepository.save(carEvent);
   }

}
