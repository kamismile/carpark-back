package ru.neoflex.microservices.carpark.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Data
public class NextStatus implements Serializable {
    private Long carId;
    private String nextStatus;
    private Date nextStatusDate;
    private PreorderType type;
}
