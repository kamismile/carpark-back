package ru.vtb.microservices.carpark.cars.exception;

public class TransitionUnsupportedException extends RuntimeException {

    private static final String MESSAGE = "Unsupported '%s' for state '%s'";

    private TransitionUnsupportedException(String message) {
        super(message);
    }

    public TransitionUnsupportedException(String event, String state) {
        this(String.format(MESSAGE, event, state));
    }

    public TransitionUnsupportedException(String message, String event, String state) {
        this(String.format(message + ". " + MESSAGE, event, state));
    }
}
