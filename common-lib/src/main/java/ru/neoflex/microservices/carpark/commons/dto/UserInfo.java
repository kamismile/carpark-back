package ru.neoflex.microservices.carpark.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @author mirzoevnik
 */
@Data
@AllArgsConstructor
@Setter
public class UserInfo {

    private String name;

    private String role;

    private Long locationId;

    public UserInfo() {
    }
}