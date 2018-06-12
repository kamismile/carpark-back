/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.preorders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;
import ru.vtb.microservices.carpark.preorders.api.PreorderApi;
import ru.vtb.microservices.carpark.preorders.model.Preorder;
import ru.vtb.microservices.carpark.preorders.service.PreorderService;

import java.util.List;

/**
 * Rest-контроллер для обработки предзаказов.
 *
 * @author Denis_Begun
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreorderController implements PreorderApi {

    private final PreorderService preorderService;

    private final JavaMailSender emailSender;

    @Override
    public List<Preorder> getAllPreorders(UserInfo userInfo) {
        return preorderService.findAll();
    }

    @Override
    public Preorder getPreorder(UserInfo userInfo, @PathVariable(name = "id") Long id) {
        return preorderService.getPreorder(id);
    }

    @Override
    public Preorder addPreorder(UserInfo userInfo, @RequestBody Preorder preorder) {
        preorder.setCreatedByUser(userInfo.getName());
        return preorderService.addPreorder(preorder, userInfo);
    }

    @GetMapping(value = "send", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMail(@RequestParam("to") String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setSubject("Автомобиль ожидает Вас в пункте проката");
        message.setText("Автомобиль ожидает Вас в пункте проката");
        emailSender.send(message);
        return "ok";

    }
}
