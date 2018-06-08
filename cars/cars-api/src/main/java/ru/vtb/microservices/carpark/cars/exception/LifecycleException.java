/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.cars.exception;

/**
 * Исключение для жизненного цикла.
 *
 * @author Denis_Begun
 */
public class LifecycleException extends RuntimeException {

    public LifecycleException(String message, Exception ex) {
        super(message, ex);
    }

}
