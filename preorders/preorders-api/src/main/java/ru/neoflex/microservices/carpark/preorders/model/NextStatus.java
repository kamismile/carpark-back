package ru.neoflex.microservices.carpark.preorders.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class NextStatus {
    private Long carId;
    private String nextStatus;
    private Date nextStatusDate;
}
