package ru.neoflex.microservices.carpark.preorders.model;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class NextStatus {
    private String nextStatus;
    private Date nextStatusDate;
}
