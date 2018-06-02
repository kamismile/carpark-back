package ru.neoflex.microservices.carpark.preorders.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CarNotAvailableException extends RuntimeException {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    private static final String MESSAGE = "Preorder could not be created, car already booked for dates:  '%s' - '%s'";

    private CarNotAvailableException(String message) {
        super(message);
    }

    public CarNotAvailableException(Date start, Date end) {
        this(String.format(MESSAGE, start, end));
    }


}