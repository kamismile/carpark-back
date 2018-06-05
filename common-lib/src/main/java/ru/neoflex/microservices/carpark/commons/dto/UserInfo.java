package ru.neoflex.microservices.carpark.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mirzoevnik
 */
@Data
@AllArgsConstructor
@Setter
public class UserInfo implements Serializable {

    private String name;

    private String role;

    private Long locationId;

    public UserInfo() {
      super();
    }
}