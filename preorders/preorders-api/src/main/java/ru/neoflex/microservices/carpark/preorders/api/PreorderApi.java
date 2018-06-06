package ru.neoflex.microservices.carpark.preorders.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;

import java.util.List;

/**
 * @author mirzoevnik
 */
public interface PreorderApi {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Preorder> getAllPreorders(UserInfo userInfo);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Preorder getPreorder(UserInfo userInfo, @PathVariable Long id);

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    Preorder addPreorder(UserInfo userInfo, @RequestBody Preorder preorder);

}
