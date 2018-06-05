package ru.neoflex.microservices.carpark.preorders.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PreorderException extends RuntimeException {

    public PreorderException(String message) {
        super(message);
    }

}