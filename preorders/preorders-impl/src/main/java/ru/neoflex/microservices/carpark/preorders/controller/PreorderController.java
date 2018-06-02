package ru.neoflex.microservices.carpark.preorders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.microservices.carpark.commons.dto.UserInfo;
import ru.neoflex.microservices.carpark.commons.model.Command;
import ru.neoflex.microservices.carpark.preorders.api.PreorderApi;
import ru.neoflex.microservices.carpark.preorders.model.NextStatus;
import ru.neoflex.microservices.carpark.preorders.model.NextStatusEvent;
import ru.neoflex.microservices.carpark.preorders.model.Preorder;
import ru.neoflex.microservices.carpark.preorders.service.KafkaProducerService;
import ru.neoflex.microservices.carpark.preorders.service.PreorderService;

import java.util.Date;
import java.util.List;

/**
 * @author mirzoevnik
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreorderController implements PreorderApi {

    private final PreorderService preorderService;

    private final KafkaProducerService kafkaService;

    @Override
    public List<Preorder> getAllPreorders(UserInfo userInfo) {
        return preorderService.findAll();
    }

    @Override
    public Preorder getPreorder(UserInfo userInfo, Long id) {
        return preorderService.getPreorder(id);
    }

    @Override
    public Preorder addPreorder(UserInfo userInfo, Preorder preorder) {
        preorder.setCreatedByUser(userInfo.getName());
        makeNotification (userInfo, preorder.getCarId());
        return preorderService.addPreorder(preorder);
    }

    @Override
    public void deletePreoder(UserInfo userInfo, Long id) {
        Long carId = preorderService.getPreorder(id).getCarId();
        makeNotification (userInfo, carId);
        preorderService.deletePreoder(id);
    }

    @Override
    public Preorder updatePreorder(UserInfo userInfo, Preorder preorder) {
        preorder = preorderService.updatePreorder(preorder);
        makeNotification (userInfo, preorder.getCarId());
        return preorder;
    }

    private void makeNotification(UserInfo userInfo, Long carId) {
        NextStatus ns = preorderService.getNextStatusForCar(carId);
        NextStatusEvent nse = new NextStatusEvent();
        nse.setCommand(Command.UPDATE);
        nse.setEntity(ns);
        nse.setUserInfo(userInfo);
        nse.setMessageDate(new Date());
        kafkaService.sendMessage(nse);
    }
}
