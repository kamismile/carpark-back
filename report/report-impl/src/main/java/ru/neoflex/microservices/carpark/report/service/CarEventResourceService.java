package ru.neoflex.microservices.carpark.report.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.microservices.carpark.report.model.CarEvent;
import ru.neoflex.microservices.carpark.report.repository.CarEventRepository;

/**
 * @author rmorenko
 */
@Service
@Data
public class CarEventResourceService {

   @Autowired
   CarEventRepository carEventRepository;

   public void add(CarEvent carEvent){
      carEventRepository.save(carEvent);
   }

}
