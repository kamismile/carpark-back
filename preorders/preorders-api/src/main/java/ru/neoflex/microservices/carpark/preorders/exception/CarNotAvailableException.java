/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.preorders.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Исключение для ошибок заказа (недоступность машины).
 * @author Denis_Begun
 */
public class CarNotAvailableException extends RuntimeException {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    private static final String MESSAGE = "Выбранные даты недоступны, уже есть предзаказ на даты:  '%s' - '%s'";

    private CarNotAvailableException(String message) {
        super(message);
    }

    public CarNotAvailableException(Date start, Date end) {
        this(String.format(MESSAGE, start, end));
    }


}