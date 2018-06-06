package ru.neoflex.microservices.carpark.preorders.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

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