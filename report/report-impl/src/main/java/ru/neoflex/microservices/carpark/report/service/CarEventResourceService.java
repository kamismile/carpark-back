package ru.neoflex.microservices.carpark.report.service;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.cars.model.Car;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.report.model.CarCommand;
import ru.neoflex.microservices.carpark.report.model.CarEvent;
import ru.neoflex.microservices.carpark.report.repository.CarEventRepository;


/**
 * @author rmorenko
 */
@Service
@Data
public class CarEventResourceService {

   @Autowired
   private CarEventRepository carEventRepository;

   public void save(CarCommand carCommand){
      Car car = carCommand.getEntity();
      CarEvent carEvent = new CarEvent();
      carEvent.setMessageDate(carCommand.getMessageDate());
      carEvent.setUserName(carCommand.getUserInfo().getName());
      BeanUtils.copyProperties(car,carEvent);
      carEvent.setId(null);
      carEvent.setCarId(car.getId());
      carEvent.setMessageType(carCommand.getCommand().toString());
      carEventRepository.save(carEvent);
   }

}
