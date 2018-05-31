package ru.neoflex.microservices.carpark.access.service;

import com.google.common.base.Optional;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.neoflex.microservices.carpark.access.model.AccessExpresion;
import ru.neoflex.microservices.carpark.access.repository.AccessExpressionRepository;

import java.util.List;

/**
 * @author rmorenko
 */
@Service
@Getter
@Setter
public class AccessExpressionService {

        @Autowired
        private AccessExpressionRepository repository;

        public AccessExpresion getByOperation(String operation) {
               return repository.findByOperation(operation).stream()
                       .findFirst().orElse(new AccessExpresion());
        }

        public List<AccessExpresion> getAll() {
                return repository.findAll();
        }


        public AccessExpresion add(AccessExpresion expresion) {
             return repository.save(expresion);
        }

        public void delete(Long id) {
             repository.delete(id);
        }

        public void update(AccessExpresion expresion) {
            repository.save(expresion);
        }

        public AccessExpresion get(Long id) {
                return repository.findOne(id);
        }
}
