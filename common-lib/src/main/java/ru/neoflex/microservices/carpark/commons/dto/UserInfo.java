package ru.neoflex.microservices.carpark.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author mirzoevnik
 */
@Data
@AllArgsConstructor
public class UserInfo {

    private String name;

    private String role;

    private Long locationId;
}