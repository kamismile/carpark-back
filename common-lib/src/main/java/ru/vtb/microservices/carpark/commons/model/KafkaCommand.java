/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2017 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.commons.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.vtb.microservices.carpark.commons.dto.UserInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Kafka command.
 * @param <T> - entuty type
 * @author Roman_Morenko
 */
@Data
@EqualsAndHashCode
@ToString
public class KafkaCommand<T extends Serializable> implements Serializable {
    private Command command;
    private UserInfo userInfo;
    private Date messageDate;
    private T entity;
    private T oldEntity;
}
