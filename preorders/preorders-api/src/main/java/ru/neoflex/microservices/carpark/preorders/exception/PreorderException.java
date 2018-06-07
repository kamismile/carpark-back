/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */
package ru.neoflex.microservices.carpark.preorders.exception;

/**
 * Исключение для ошибок заказа.
 * @author Denis_Begun
 */
public class PreorderException extends RuntimeException {

    public PreorderException(String message) {
        super(message);
    }

}