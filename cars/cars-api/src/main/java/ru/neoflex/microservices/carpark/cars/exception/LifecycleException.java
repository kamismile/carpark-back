package ru.neoflex.microservices.carpark.cars.exception;

public class LifecycleException extends RuntimeException {

    public LifecycleException(String message, Exception ex) {
        super(message, ex);
    }

}
