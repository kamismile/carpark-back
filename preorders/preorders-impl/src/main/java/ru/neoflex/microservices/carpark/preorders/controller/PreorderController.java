package ru.neoflex.microservices.carpark.preorders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.preorders.api.PreorderApi;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.service.PreorderService;

import java.util.List;

/**
 * @author mirzoevnik
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreorderController implements PreorderApi {

    private final PreorderService preorderService;

    @Override public List<Preorder> getPreorders() {
        return preorderService.findAll();
    }
}
